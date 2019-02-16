# TabPagerManager for Android



## Step 1. Download

  Download the ```TabPagerManager.java```(*[click here](https://codeload.github.com/BarefootBKK/TabPagerManager-for-Android/zip/master)*) file and copy it to your Android project.
  
  
## Step 2. How to use it ?


### ***General***

  ```
  TabPagerManager.with(fragmentManager)
                  .setTabPager(tabLayout, viewPager)
                  .add("tabName_1", new Fragment())
                  .add("tabName_2", new Fragment())
                  .commit();
  ```
  Notice: 
  
  - replace the "new Fragment()" above with your own fragment.
  
  - If you're in activity, use "getSupportFragmentManager()" to be the target fragmentManager. 
  
  - If you're in fragment, use "getChildFragmentManager()".


### ***More***

  - **Defualt indicator mode**
  
    The default indicator line mode is ***EQUAL_TEXT***, which makes your tabs look like following :
  
      > <img src="https://camo.githubusercontent.com/f20f015ed02f7ec3a86ff4159e9bd24249b6897e/687474703a2f2f7468797273692e636f6d2f74362f3636392f3135343937323938373178323839303137333735332e706e67" width="350" alt="EQUAL_TEXT" />
  
  - **Other modes**
  
    - *EQUAL_TAB_CENTER*
  
    > <img src="https://camo.githubusercontent.com/68022916b8776dd9f3d7f332cce93da5a2c360df/687474703a2f2f7468797273692e636f6d2f74362f3636392f3135343937333034373578323839303137343039342e6a7067" width="350" alt="EQUAL_TAB_CENTER" />
  
    - *EQUAL_TAB_FILL*
    
    > <img src="https://camo.githubusercontent.com/f3c4dacabc4d8eb49f38dbfec2020be0cb6725fe/687474703a2f2f7468797273692e636f6d2f74362f3636392f3135343937333035393178323839303137343039342e706e67" width="350" alt="EQUAL_TAB_FILL" />
  
  
 - **Use it in your code**
  
    ```
    TabPagerManager.with(fragmentManager)
                    .setTabPager(tabLayout, viewPager)
                    .add("tabName_1", new Fragment())
                    .add("tabName_2", new Fragment())
                    .setIndicatorLineMode(TabPagerManager.EQUAL_TAB_FILL)
                    .commit();
    ```
  
  
  ### ***Advanced***
  
  - **Set tabs' interval**
    
    This function only works on mode  *EQUAL_TEXT*.
    
    ```
    setTabInterval(int tabInterval)
    
    e.g.
    setTabInterval(20)
    ```
    
  - **Set selected tab**
  
    Use function: ```add(String tabTitle, Fragment fragment, Boolean isSelected)```.
  
    ```
    TabPagerManager.with(fragmentManager)
                    .setTabPager(tabLayout, viewPager)
                    .add("tabName_1", new Fragment())
                    .add("tabName_2", new Fragment(), true)
                    .commit();
    ```
    
  - **Also, you can initialize your tabLayout and viewPager before using "TabPagerManager"**
  
    e.g.
    
    Initialize
    
    ```
    tabLayout.set...
    viewPager.set...
    ```
    
    Bind with TabPagerManager
    
    ```
    TabPagerManager.with(fragmentManager)
                    .setTabPager(tabLayout, viewPager)
                    .add("tabName", new Fragment())
                    .commit();
    ```
