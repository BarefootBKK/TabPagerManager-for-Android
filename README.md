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

- ***More***
  The default indicator line mode is "EQUAL_TEXT", which makes your tab looks like following :
  
  <img src="https://camo.githubusercontent.com/f20f015ed02f7ec3a86ff4159e9bd24249b6897e/687474703a2f2f7468797273692e636f6d2f74362f3636392f3135343937323938373178323839303137333735332e706e67" width="400" alt="EQUAL_TEXT" />

  Also, you can have more choice:
  
