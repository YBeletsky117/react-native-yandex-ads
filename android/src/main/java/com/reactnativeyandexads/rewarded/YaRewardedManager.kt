package com.reactnativeyandexads.rewarded

import com.facebook.react.bridge.*
import com.yandex.mobile.ads.common.AdRequest
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.rewarded.Reward
import com.yandex.mobile.ads.rewarded.RewardedAd
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener
import com.reactnativeyandexads.utils.Res.Rewarded.REACT_CLASS


class YaRewardedManager(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext), RewardedAdEventListener, LifecycleEventListener {
    private var mPromise: Promise? = null
    private var mDidClick = false
    private var mViewAtOnce = false
    private var mRewarded: RewardedAd? = null
    @ReactMethod
    fun showAd(adUnitId: String?, p: Promise) {
        if (mPromise != null) {
            p.reject("E_FAILED_TO_SHOW", "Only one `showAd` can be called at once")
            return
        }
        val reactContext = this.reactApplicationContext
        mRewarded = RewardedAd(reactContext)
        mRewarded!!.setAdUnitId(adUnitId!!)
        mRewarded!!.setRewardedAdEventListener(this)
        mViewAtOnce = true
        mPromise = p
        mRewarded!!.loadAd(AdRequest.Builder().build())
    }

    override fun getName(): String = REACT_CLASS

    private fun cleanUp() {
        mPromise = null
        mDidClick = false
        mViewAtOnce = false
        if (mRewarded != null) {
            mRewarded!!.destroy()
            mRewarded = null
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
            mRewarded!!.show()
        }
    }

    override fun onAdFailedToLoad(adRequestError: AdRequestError) {
        mPromise!!.reject("E_FAILED_TO_LOAD", adRequestError.description)
        cleanUp()
    }

    override fun onAdShown() {}
    override fun onAdDismissed() {
        cleanUp()
    }

    override fun onRewarded(reward: Reward) {
        val event = Arguments.createMap()
        event.putInt("amount", reward.amount)
        event.putString("type", reward.type)
        event.putBoolean("click", mDidClick)
        mPromise!!.resolve(event)
        cleanUp()
    }

    override fun onLeftApplication() {}
    override fun onReturnedToApplication() {}

    init {
        reactContext.addLifecycleEventListener(this)
    }
}
