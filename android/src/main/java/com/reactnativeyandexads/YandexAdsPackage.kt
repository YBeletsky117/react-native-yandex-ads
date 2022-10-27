package com.reactnativeyandexads
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager
import com.reactnativeyandexads.banner.YABannerViewManager
import com.reactnativeyandexads.common.YaContainerView
import com.reactnativeyandexads.common.YaImageView
import com.reactnativeyandexads.common.YaMediaView
import com.reactnativeyandexads.common.YaTextView
import com.reactnativeyandexads.native.YaNativeViewManager
import com.reactnativeyandexads.interstitial.YaInterstitialManager
import com.reactnativeyandexads.rewarded.YaRewardedManager


class YandexAdsPackage : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
      val modules: MutableList<NativeModule> = java.util.ArrayList()
      modules.add(YaInterstitialManager(reactContext))
      modules.add(YaRewardedManager(reactContext))
      modules.add(YandexAdsViewManager(reactContext))
      return modules
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return listOf(
          YABannerViewManager(),
          YaTextView(),
          YaImageView(),
          YaMediaView(),
          YaContainerView(),
          YaNativeViewManager(reactContext)
        )
    }
}
