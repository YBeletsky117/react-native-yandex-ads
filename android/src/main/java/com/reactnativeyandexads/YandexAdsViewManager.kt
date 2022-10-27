package com.reactnativeyandexads
import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.yandex.mobile.ads.common.MobileAds
import com.reactnativeyandexads.utils.Res.MAIN_REACT_CLASS

class YandexAdsViewManager(context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {

  private val reactContext: ReactApplicationContext = context

  override fun getName() = MAIN_REACT_CLASS

  @ReactMethod
  fun initialize(
    isDebug: Boolean,
    userLocation: Boolean,
    userConsent: Boolean,
  ) {
    MobileAds.initialize(reactContext) {
      Log.d("RNYandexAdsMobile","Yandex Ads initialized")
    }
    MobileAds.enableDebugErrorIndicator(isDebug)
    MobileAds.setLocationConsent(userLocation)
    MobileAds.setUserConsent(userConsent)
  }
}
