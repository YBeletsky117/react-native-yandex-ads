package com.reactnativeyandexads.interstitial

import com.facebook.react.bridge.*
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.interstitial.InterstitialAd
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener
import com.reactnativeyandexads.utils.Res.Interstitial.REACT_CLASS


class YaInterstitialManager(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext), InterstitialAdEventListener, LifecycleEventListener {
    private var mPromise: Promise? = null
    private var mDidClick = false
    private var mViewAtOnce = false
    private var mInterstitial: InterstitialAd? = null
    @ReactMethod
    fun showAd(adUnitId: String?, p: Promise) {
        if (mPromise != null) {
            p.reject("E_FAILED_TO_SHOW", "Only one `showAd` can be called at once")
            return
        }
        val reactContext = this.reactApplicationContext
        mInterstitial = InterstitialAd(reactContext)
        mInterstitial!!.setAdUnitId(adUnitId!!)
        mInterstitial!!.setInterstitialAdEventListener(this)
        mViewAtOnce = true
        mPromise = p
        mInterstitial!!.loadAd(AdRequest.Builder().build())
    }

    override fun getName(): String = REACT_CLASS

    private fun cleanUp() {
        mPromise = null
        mDidClick = false
        mViewAtOnce = false
        if (mInterstitial != null) {
            mInterstitial!!.destroy()
            mInterstitial = null
        }
    }

    override fun onHostResume() {}
    override fun onHostPause() {}
    override fun onImpression(impressionData: ImpressionData?) {}
    override fun onAdClicked() {
        mDidClick = true
    }

    override fun onHostDestroy() {
        cleanUp()
    }

    override fun onAdLoaded() {
        if (mViewAtOnce) {
            mInterstitial!!.show()
        }
    }

    override fun onAdFailedToLoad(adRequestError: AdRequestError) {
        mPromise!!.reject("E_FAILED_TO_LOAD", adRequestError.description)
        cleanUp()
    }

    override fun onAdShown() {}
    override fun onAdDismissed() {
        mPromise!!.resolve(mDidClick)
        cleanUp()
    }

    override fun onLeftApplication() {
        mDidClick = true
    }

    override fun onReturnedToApplication() {}

    init {
        reactContext.addLifecycleEventListener(this)
    }
}
