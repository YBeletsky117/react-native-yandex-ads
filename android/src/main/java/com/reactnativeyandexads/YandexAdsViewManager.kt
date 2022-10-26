package com.reactnativeyandexads
import android.graphics.Color
import android.view.View
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class YandexAdsViewManager : SimpleViewManager<View>() {
  override fun getName() = "YandexAdsView"

  override fun createViewInstance(reactContext: ThemedReactContext): View {
    return View(reactContext)
  }

  @ReactProp(name = "color")
  fun setColor(view: View, color: String) {
    view.setBackgroundColor(Color.parseColor(color))
  }
}
