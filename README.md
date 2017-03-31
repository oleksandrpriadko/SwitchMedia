# SwitchMedia
SwitchMedia. Android Application Technical Test Carousels

#Release Notes:
* minSdkVersion 16; targetSdkVersion 25; compileSdkVersion 25; buildToolsVersion "25.0.1"
* MVP pattern
* Single activity (fragments/actionBar)
* Tablet form factor
* Phone form factor
* Carousel functionality. Vertical RecyclerView (all available screen size), which consists of child RecyclerViews of horizontal orientation. All horizontal rows scroll independently.
* I used simple randomness. I do not know if it is what you need but it will show you how carousel works.
* Async load and show a 2D data array of simple title/image elements, array caching(expiration time - only 10 seconds as we receive random data from server, can be increased). I chose hardest way - perform as much requests to server as items needed.
* Splash screens disappears when all initial data loaded (data preloading)
* Titles and images(by Picasso) load async. Images caching(memory, disk). Green inidicator in top left corner - loaded from memory, red - from disk, blue - from network. If you would like to see how I work with LRU cache and disk cache - contact me.
* Click on item displays semi-transparent overlay of the title/image.
* Android binary (APK) compiled in "release" configuration is in root folder.

#Note! 
* compile 'com.android.support:appcompat-v7:25.0.1' and compile 'com.android.support:design:25.0.1' used for support MaterialDesign on platforms < v21.
* If you would like to see usage of additional libraries - contact me and I will use them.
* If you would like to see different behaviour/appearance of UI elements - contact me and I will implement it.
* Tested on Nexus 5(API 24), Nexus 6(API 25), emulator API 25(Nexus 4, Nexus 6P, Nexus 5X, Nexus Pixel C, Pixel, Pixel XL, Nexus 9, Nexus 10), Nexus 5((API 16).

* Main Screen
![Alt text](https://cloud.githubusercontent.com/assets/9527746/24550673/d6a40626-1627-11e7-8d9b-98279b07c674.png)

* Tab selected
![Alt text](https://cloud.githubusercontent.com/assets/9527746/24550679/d8ac302e-1627-11e7-99d3-4e231c3fcac9.png)

* Item selected
![Alt text](https://cloud.githubusercontent.com/assets/9527746/24550680/da2be9bc-1627-11e7-9522-e6f4f9deb497.png)
