 <h1 align="center">Huawei-Mopub Mediation Github Documentation</h3>

# Introduction

In this documentation we explained how to use Huawei-Mopub mediation with in the different platforms.

# Compatibility

|   | Banner Ad | Interstitial Ad |

# How to start?
  
## Create an ad unit on Huawei Publisher Service

1. Sign in to [Huawei Developer Console] (https://developer.huawei.com/consumer/en/console) and create an AdUnit

## Create a custom event on Google AdMob

1. Sign in to [Mopub console] ([https://accounts.smaato.com/](https://accounts.smaato.com/))
2. Go to "**Inventory -> Create a new App" 
3. Under the "**Apps**" section, click "**New Adspace**" Give it a label (eg: Huawei Banner)  click "**Save**"
4. Go to "**Networks -> Create a new Network"  and choose Custom SDK Network
5. Click New Line Item for add mediation
6. Enter the **Class Name and Method Name ** and **Custom Data** according to the type of your Ad. Refer to the section below.

## Custom event class
| Ad Type        | Custom event class           |
| ------------- |:-------------:|
| Banner Ad      | com.hmsmd.huawei.ads.CustomMediationBanner |
| Interstitial Ad      | com.hmsmd.huawei.ads.CustomMediationIntersitial     |

## Custom Event Parameters
```
{"AD_UNIT_ID":"testw6vs28auh3"}
```
## Method Names
```
loadCustomBanner
loadCustomIntersitial
```

# Integrate the Huawei Mediation SDK

**Note** : A device with Huawei Mobile Services (HMS) installed is required

In the **project-level** build.gradle, include Huawei's Maven repository.

```groovy
repositories {
    google()
    jcenter() // Also, make sure jcenter() is included
    maven { url 'https://developer.huawei.com/repo/' } // Add this line
}

...

allprojects {
    repositories {
        google()
        jcenter() // Also, make sure jcenter() is included
        maven { url 'https://developer.huawei.com/repo/' } //Add this line
    }
}
```

In the app-level build.gradle, include Huawei Ads dependency (required by the adapter) and the adapter

```groovy
dependencies {
    implementation 'com.huawei.hms:ads-lite:13.4.37.300'
    implementation 'com.hmsmd.huawei.ads:androidlibrary:1.0.4'
}
```

1. Generate a keystore file

2. Place the keystore file in the app directory

3. Modify the app module build.gradle file - update the signingConfigs to your keystore's setting

4. Build the project or run the app in your device / emulator

**Important:** _To add Huawei Ads Kit SDK and Mediation adapter, the native project should be opened with Android Studio._

# Platforms

## Native

This section demonstrates how to use Smaato mediation feature with Huawei Ads Kit on Native android app.

Firstly, integrate the Smaato SDK for Android

[Smaato Android SDK](https://developers.smaato.com/publishers-legacy/android-sdk-getting-started) can be used for all ad types.

**Note** : Developers can find app level build.gradle in their project from __**"app-folder/app/build.gradle"**__

### **Banner Ad**

To use _Banner_ ads in Native android apps, please check the Smaato SDK. Click [here](https://developers.smaato.com/publishers-legacy/android-sdk-adformat-banners) to get more information about Smaato SDKs _Banner_ Ad development.

### **Interstitial Ad**

To use Interstitial ads in Native android apps, please check the Smaato SDK. Click [here](https://developers.smaato.com/publishers-legacy/android-sdk-adformat-interstitial) to get more information about Smaato SDKs Interstitial Ad development.

## **Sample Codes Based on Ad Types**

### **Banner Ad**

```jsx
<SmaatoBanner
adSize="fullBanner"
BannerView mBanner = new BannerView (context);
mBanner.getAdSettings().setPublisherId(publisherId);
mBanner.getAdSettings().setAdspaceId(adspaceId);} />
```

### **Interstitial Ad**

```jsx
interstitial = new Interstitial(this); //'this' is your Context
interstitial.setInterstitialAdListener(interstitialAdListener);
interstitial.getAdSettings().setPublisherId(your_publisher_id);
interstitial.getAdSettings().setAdspaceId(your_adspace_d);
```


# Screenshots

## AdMob
<table>
<tr>
<td>
<img src="https://user-images.githubusercontent.com/41696219/109941743-8626a780-7ce4-11eb-8aa8-4da1e3f1092f.png" width="200">

Banner Ad
</td>

<td>
<img src="https://user-images.githubusercontent.com/41696219/109941887-aa828400-7ce4-11eb-9409-d93adf506724.JPG" width="200">

Interstitial Ad
</td>

<td>
<img src="https://user-images.githubusercontent.com/41696219/109941974-c4bc6200-7ce4-11eb-81b8-e35a2589d528.JPG" width="200">

Rewarded Ad
</td>
<td>
<img src="https://user-images.githubusercontent.com/41696219/109942031-d43bab00-7ce4-11eb-815f-403918a6b7f4.png" width="200">

Native Ad
</td>
</tr>
</tr>
</table>

## Huawei
<table>
<tr>
<td>
<img src="https://user-images.githubusercontent.com/41696219/109942123-ee758900-7ce4-11eb-96a3-11cce5454c51.png" width="200">

Banner Ad
</td>

<td>
<img src="https://user-images.githubusercontent.com/41696219/109939330-01d32500-7ce2-11eb-9e39-6a9237ca8c54.JPG" width="200">


Interstitial Ad
</td>

<td>
<img src="https://user-images.githubusercontent.com/41696219/109942227-0baa5780-7ce5-11eb-8b69-086924473db0.png" width="200">

Rewarded Ad
</td>
<td>
<img src="https://user-images.githubusercontent.com/41696219/109942307-211f8180-7ce5-11eb-8c3e-7d21903bf6d1.png" width="200">

Native Ad
</td>

</tr>
</tr>
</table>




