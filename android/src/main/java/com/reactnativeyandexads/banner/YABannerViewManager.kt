package com.reactnativeyandexads.banner

import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.yandex.mobile.ads.banner.AdSize
import com.reactnativeyandexads.utils.Res.Banner.REACT_CLASS


class YABannerViewManager : SimpleViewManager<YABannerView?>() {

  override fun getName(): String = REACT_CLASS

  @ReactProp(name = "adUnitID")
  fun setAdUnitID(view: YABannerView, adUnitID: String?) {
    view.setAdUnitID(adUnitID)
  }


  @ReactProp(name = "requestParams")
  fun setRequestParams(view: YABannerView, source: ReadableMap?) {
    if (source is ReadableMap) {
      view.setRequestParams(source.toHashMap() as MutableMap<String, String>)
    }
  }


  @ReactProp(name = "customSize")
  fun setCustomSize(view: YABannerView, sizes: ReadableArray) {
    val adSize = AdSize.flexibleSize(sizes.getInt(0), sizes.getInt(1))
    view.setSize(adSize)
  }

  @ReactProp(name = "size")
  fun setSize(view: YABannerView, size: String?) {
    val adSize: AdSize = when (size) {
      "BANNER_300x250" -> AdSize.flexibleSize(300, 250)
      "BANNER_320x250" -> AdSize.flexibleSize(320, 250)
      "BANNER_300x300" -> AdSize.flexibleSize(300, 300)
      "BANNER_320x50" -> AdSize.flexibleSize(320, 50)
      "BANNER_320x100" -> AdSize.flexibleSize(320, 100)
      "BANNER_400x240" -> AdSize.flexibleSize(400, 240)
      "BANNER_728x90" -> AdSize.flexibleSize(728, 90)
      else -> AdSize.flexibleSize(240, 400)
    }
    view.setSize(adSize)
  }

  override fun createViewInstance(reactContext: ThemedReactContext): YABannerView {
    return YABannerView(reactContext)
  }

  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any>? {
    return MapBuilder.of(
      "onDidTrackImpression",
      MapBuilder.of("registrationName", "onDidTrackImpression"),
      "onClick",
      MapBuilder.of("registrationName", "onClick"),
      "onDidLoad",
      MapBuilder.of("registrationName", "onDidLoad"),
      "onDidFailLoading",
      MapBuilder.of("registrationName", "onDidFailLoading"),
      "onWillLeaveApp",
      MapBuilder.of("registrationName", "onWillLeaveApp"),
      "onDidReturnedToApplication",
      MapBuilder.of("registrationName", "onDidReturnedToApplication"),
    )
  }
}
