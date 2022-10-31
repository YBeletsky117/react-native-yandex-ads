package com.reactnativeyandexads.banner

import android.annotation.SuppressLint
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.events.RCTEventEmitter
import com.facebook.react.views.view.ReactViewGroup
import com.yandex.mobile.ads.banner.AdSize
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData


@SuppressLint("ViewConstructor")
class YABannerView(context: ThemedReactContext) : ReactViewGroup(context),
  BannerAdEventListener, LifecycleEventListener {
  private var requestParams: MutableMap<String, String>? = null
  private val mContext: ReactContext
  private var adView: BannerAdView? = null
  private var mAdUnitID: String? = null
  private var mSize: AdSize? = null
  private val mEventEmitter: RCTEventEmitter

  fun setAdUnitID(adUnitID: String?) {
    mAdUnitID = adUnitID
    setupView()
  }

  fun setSize(size: AdSize?) {
    mSize = size
    setupView()
  }

  fun setRequestParams(params: MutableMap<String, String>?) {
    requestParams = params
    setupView()
  }

  private fun setupView() {
    if (adView == null && mAdUnitID != null && mSize != null) {
      adView = BannerAdView(context)
      adView!!.setAdUnitId(mAdUnitID!!)
      adView!!.setAdSize(mSize!!)
      // Creating an ad targeting object
      val adRequest = AdRequest.Builder()
        .setParameters(requestParams ?: HashMap())
        .build()
      // Registering a listener to track events that occur in banner ads
      adView!!.setBannerAdEventListener(this)
      // Load ads
      adView!!.loadAd(adRequest)
    }
  }

  override fun onHostResume() {}

  override fun onHostPause() {}

  override fun onImpression(impressionData: ImpressionData?) {
    val event = Arguments.createMap()
    event.putString(AD_ID, mAdUnitID)
    event.putString("data", impressionData.toString())
    sendEvent(AdEvents.onDidTrackImpression.name, event)
  }

  override fun onAdClicked() {
    val event = Arguments.createMap()
    event.putString(AD_ID, mAdUnitID)
    sendEvent(AdEvents.onClick.name, event)
  }

  override fun onHostDestroy() {
    adView?.destroy()
  }

  override fun onAdLoaded() {
    removeAllViews()
    val r = mContext.resources
    val dm = r.displayMetrics
    val pxW = if (mSize!!.width > 0) dp2px(mSize!!.width, dm) else r.displayMetrics.widthPixels
    val pxH = dp2px(mSize!!.height, dm)
    adView?.measure(pxW, pxH)
    adView?.layout(0, 0, pxW, pxH)
    addView(adView)
    val event = Arguments.createMap()
    event.putString(AD_ID, mAdUnitID)
    sendEvent(AdEvents.onDidLoad.name, event)
  }

  private fun dp2px(dp: Int, dm: DisplayMetrics): Int {
    return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), dm))
  }

  override fun onAdFailedToLoad(adRequestError: AdRequestError) {
    val event = Arguments.createMap()
    event.putString(AD_ID, mAdUnitID)
    event.putString("errorMessage", adRequestError.description)
    sendEvent(AdEvents.onDidFailLoading.name, event)
    adView = null
  }

  override fun onLeftApplication() {
    val event = Arguments.createMap()
    event.putString(AD_ID, mAdUnitID)
    sendEvent(AdEvents.onWillLeaveApp.name, event)
  }

  override fun onReturnedToApplication() {
    val event = Arguments.createMap()
    event.putString(AD_ID, mAdUnitID)
    sendEvent(AdEvents.onDidReturnedToApplication.name, event)
  }

  private fun sendEvent(name: String, event: WritableMap?) {
    val reactContext = context as ReactContext
    reactContext.getJSModule(RCTEventEmitter::class.java).receiveEvent(
      id,
      name,
      event
    )
  }

  init {
    mContext = context
    mContext.addLifecycleEventListener(this)
    mEventEmitter = mContext.getJSModule(RCTEventEmitter::class.java)
  }

  private companion object {
    const val AD_ID = "adUnitID"

    enum class AdEvents {
      onDidLoad,
      onClick,
      onDidTrackImpression,
      onDidFailLoading,
      onWillLeaveApp,
      onDidReturnedToApplication,
    }
  }
}
