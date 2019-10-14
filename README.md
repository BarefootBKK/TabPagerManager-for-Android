# TabPagerManager for Android



## Step 1. Download

  Download the ```TabPagerManager.java```(*[click here](https://codeload.github.com/BarefootBKK/TabPagerManager-for-Android/zip/master)*) file and copy it to your Android project.
  
  
## Step 2. How to start ?


### ***General***

  ```
  TabPagerManager.with(fragmentManager)
                 .setTabPager(tabLayout, viewPager)
                 .add("tabName_1", new Fragment())
                 .add("tabName_2", new Fragment())
                 .commit();
  ```
  Notice: 
  
  - Replace the "new Fragment()" above with your own fragment.
  
  - If you're in activity, use "getSupportFragmentManager()" to be the target fragmentManager. 
  
  - If you're in fragment, use "getChildFragmentManager()".


### ***More***

  - **Defualt indicator mode**
  
    The default indicator line mode is ***EQUAL_TEXT***, which makes your tabs look like following :
  
      > <img src="https://img-blog.csdnimg.cn/20190210020150447.png" width="350" alt="EQUAL_TEXT" />
  
  - **Other modes**
  
    - *EQUAL_TAB_CENTER*
  
    > <img src="https://img-blog.csdnimg.cn/20190210020209802.jpg" width="350" alt="EQUAL_TAB_CENTER" />
  
    - *EQUAL_TAB_FILL*
    
    > <img src="https://img-blog.csdnimg.cn/20190210020227637.png" width="350" alt="EQUAL_TAB_FILL" />
  
  
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
    
  - **Also, you can initialize your tabLayout and viewPager before using "TabPagerManager".**
  
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
