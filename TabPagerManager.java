import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * author BarefootBKK
 * date 2019/02/10
 */
public class TabPagerManager {
    public static final int EQUAL_TEXT = 0;
    public static final int EQUAL_TAB_CENTER = 1;
    public static final int EQUAL_TAB_FILL = 2;
    public static final int TAB_FIXED = TabLayout.MODE_FIXED;
    public static final int TAB_SCROLLABLE = TabLayout.MODE_SCROLLABLE;
    private FragmentManager fragmentManager;

    public TabPagerManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public static TabPagerBuilder with(FragmentManager fragmentManager) {
        return new TabPagerManager(fragmentManager).getBuilder();
    }

    public TabPagerBuilder getBuilder() {
        return new TabPagerBuilder();
    }

    public class TabPagerBuilder {
        private TabLayout tabLayout;
        private ViewPager viewPager;
        private boolean isInitialized = false;

        public TabPagerOption setTabPager(TabLayout tabLayout, ViewPager viewPager) {
            this.tabLayout = tabLayout;
            this.viewPager = viewPager;
            this.isInitialized = true;
            return new TabPagerOption();
        }

        public class TabPagerOption {
            private List<Fragment> fragmentList;
            private List<String> titleList;
            private FragmentViewPagerAdapter fragmentViewPagerAdapter;
            private boolean isEqualToText = false;
            private int selectIndex = 0;
            private int tabInterval = 20;

            public TabPagerOption() {
                this.titleList = new ArrayList<>();
                this.fragmentList = new ArrayList<>();
            }

            public TabPagerOption add(String title, Fragment fragment) {
                titleList.add(title);
                fragmentList.add(fragment);
                return this;
            }

            public TabPagerOption add(String title, Fragment fragment, boolean isSelected) {
                titleList.add(title);
                fragmentList.add(fragment);
                if (isSelected) {
                    selectIndex = titleList.size() - 1;
                }
                return this;
            }

            public TabPagerOption setIndicatorLineMode(int lineMode) {
                if (lineMode == EQUAL_TEXT) {
                    tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
                    this.isEqualToText = true;
                } else if (lineMode == EQUAL_TAB_FILL) {
                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                } else {
                    tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
                }
                return this;
            }

            public TabPagerOption setTabInterval(int tabInterval) {
                this.tabInterval = tabInterval;
                return this;
            }

            public int getTabCount() {
                return titleList == null ? 0 : titleList.size();
            }

            public TabPagerOption setTabMode(final int tabMode) {
                tabLayout.setTabMode(tabMode);
                return this;
            }

            public TabPagerOption setTabRippleColor(int colorId) {
                ColorStateList colorStateList = tabLayout.getContext().getResources().getColorStateList(colorId);
                tabLayout.setTabRippleColor(colorStateList);
                return this;
            }

            public void commit() {
                if (isInitialized && getTabCount() > 0 && fragmentManager != null) {
                    fragmentViewPagerAdapter = new FragmentViewPagerAdapter(fragmentManager);
                    fragmentViewPagerAdapter.setAdapterBasicList(fragmentList, titleList);
                    viewPager.setOffscreenPageLimit(2);
                    viewPager.setAdapter(fragmentViewPagerAdapter);
                    tabLayout.setupWithViewPager(viewPager);
                    tabLayout.getTabAt(selectIndex).select();
                    if (isEqualToText) {
                        reflex();
                    }
                }
            }

            private void reflex(){
                tabLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Field tabIndicator;
                            if (Build.VERSION.SDK_INT < 28) {
                                tabIndicator = tabLayout.getClass().getDeclaredField("slidingTabIndicator");
                            } else {
                                tabIndicator = tabLayout.getClass().getDeclaredField("mTabStrip");
                            }
                            tabIndicator.setAccessible(true);
                            LinearLayout mTabStrip = (LinearLayout) tabIndicator.get(tabLayout);
                            int dp10 = dip2px(tabLayout.getContext(), tabInterval);

                            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                                View tabView = mTabStrip.getChildAt(i);
                                Field mTextViewField;
                                if (Build.VERSION.SDK_INT < 28) {
                                    mTextViewField = tabView.getClass().getDeclaredField("textView");
                                } else {
                                    mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                                }
                                mTextViewField.setAccessible(true);
                                TextView mTextView = (TextView) mTextViewField.get(tabView);
                                tabView.setPadding(0, 0, 0, 0);
                                int width = 0;
                                width = mTextView.getWidth();
                                if (width == 0) {
                                    mTextView.measure(0, 0);
                                    width = mTextView.getMeasuredWidth();
                                }
                                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                                params.width = width ;
                                params.leftMargin = dp10;
                                params.rightMargin = dp10;
                                tabView.setLayoutParams(params);
                                tabView.invalidate();
                            }
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            private int dip2px(Context context, float dpValue) {
                float scale = context.getResources().getDisplayMetrics().density;
                return (int) (dpValue * scale + 0.5f);
            }
        }
    }

    private static class FragmentViewPagerAdapter extends FragmentPagerAdapter {
        private List<String> titleList;
        private List<Fragment> fragmentList;

        public FragmentViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        public void setAdapterBasicList(List<Fragment> fragmentList, List<String> titleList) {
            this.fragmentList = fragmentList;
            this.titleList = titleList;
        }
    }
}
