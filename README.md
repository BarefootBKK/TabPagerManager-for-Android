# TabPagerManager-for-Android

## Step 1. Download

  Download the ```TabPagerManager.java``` file and copy it to your Android project.
  
## Step 2. How to use it ?

- ***General***
```
TabPagerManager.with(fragmentManager)
                .setTabPager(tabLayout, viewPager)
                .add("tabName_1", new Fragment())
                .add("tabName_2", new Fragment())
                .commit();
```

- More
  The default mode is "EQUAL_TEXT", which makes your tab looks like following :
  [](http://thyrsi.com/t6/669/1549729871x2890173753.png)
