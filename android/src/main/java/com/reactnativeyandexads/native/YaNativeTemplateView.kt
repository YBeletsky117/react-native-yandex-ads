package com.reactnativeyandexads.native

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.PixelUtil
import com.facebook.react.uimanager.events.RCTEventEmitter
import com.facebook.react.uimanager.util.ReactFindViewUtil
import com.reactnativeyandexads.utils.NativeAdConfig
import com.reactnativeyandexads.utils.NativeAdNativeIDs
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData
import com.yandex.mobile.ads.nativeads.*

@SuppressLint("ViewConstructor")
class YaNativeTemplateView(
    _reactContext: ReactApplicationContext,
    _layout: NativeAdView,
    _adUnitId: String,
    _propWidth: Int,
    _propHeight: Int,
    _nativeIDs: NativeAdNativeIDs,
    _config: NativeAdConfig,
) : View(_reactContext) {

    private val reactContext: ReactApplicationContext = _reactContext

    // NATIVE VIEW
    private val adView: NativeAdView = _layout

    // LIST OF NATIVE IDs
    private val nativeIDs: NativeAdNativeIDs = _nativeIDs

    private val adUnitId: String = _adUnitId
    private val propWidth: Int = _propWidth
    private val propHeight: Int = _propHeight
    private val config: NativeAdConfig = _config

    // LOADER
    // Initialize Ad Loader
    private val adLoader: NativeAdLoader = NativeAdLoader(_reactContext)

    // TITLE *
    private var title: TextView? = null

    // BODY
    private var body: TextView? = null

    // WARNING *
    private var warning: TextView? = null

    // SPONSORED *
    private var sponsored: TextView? = null

    // AGE
    private var age: TextView? = null

    // DOMAIN *
    private var domain: TextView? = null

    // FEEDBACK *
    private var feedback: ImageView? = null

    // MEDIA *
    private var media: MediaView? = null

    // FAVICON
    private var favicon: ImageView? = null

    // ICON
    private var icon: ImageView? = null

    init {
        reactContext.runOnUiQueueThread {

            val _title = ReactFindViewUtil.findView(adView, nativeIDs.title)
            val _body = ReactFindViewUtil.findView(adView, nativeIDs.body)
            val _warning = ReactFindViewUtil.findView(adView, nativeIDs.warning)
            val _sponsored = ReactFindViewUtil.findView(adView, nativeIDs.sponsored)
            val _age = ReactFindViewUtil.findView(adView, nativeIDs.age)
            val _domain = ReactFindViewUtil.findView(adView, nativeIDs.domain)

            val _icon = ReactFindViewUtil.findView(adView, nativeIDs.icon)
            val _favicon = ReactFindViewUtil.findView(adView, nativeIDs.favicon)
            val _feedback = ReactFindViewUtil.findView(adView, nativeIDs.feedback)

            val _media = ReactFindViewUtil.findView(adView, nativeIDs.media)

            log("UNIQ KEY: " + nativeIDs.title)

            if (_title !== null) {
                val titleView = adView.findViewById<TextView>(_title.id)
                title = titleView
            }
            if (_body !== null) {
                val bodyView = adView.findViewById<TextView>(_body.id)
                body = bodyView
            }
            if (_warning !== null) {
                val warningView = adView.findViewById<TextView>(_warning.id)
                warning = warningView
            }
            if (_sponsored !== null) {
                val sponsoredView = adView.findViewById<TextView>(_sponsored.id)
                sponsored = sponsoredView
            }
            if (_age !== null) {
                val ageView = adView.findViewById<TextView>(_age.id)
                age = ageView
            }
            if (_domain !== null) {
                val domainView = adView.findViewById<TextView>(_domain.id)
                domain = domainView
            }



            if (_icon !== null) {
                val iconView = adView.findViewById<ImageView>(_icon.id)
                icon = iconView
            }
            if (_favicon !== null) {
                val faviconView = adView.findViewById<ImageView>(_favicon.id)
                favicon = faviconView
            }
            if (_feedback !== null) {
                val feedbackView = adView.findViewById<ImageView>(_feedback.id)
                feedback = feedbackView
            }
            if (_media !== null) {
                val mediaView = adView.findViewById<MediaView>(_media.id)
                media = mediaView
            }
            // Start request Ad
            requestAd(adUnitId, toPx(propWidth), toPx(propHeight))
            // Assign listener for request loader
            adLoader.setNativeAdLoadListener(LoaderListener())
        }
    }

    private fun setupFrame() {
        val WIDTH = toPx(propWidth)
        val HEIGHT = toPx(propWidth)
        measure(
            MeasureSpec.makeMeasureSpec(WIDTH, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(HEIGHT, MeasureSpec.EXACTLY)
        )
        title?.apply {
            setTextColor(Color.parseColor(config.title_color))
            textSize = config.title_fontSize.toFloat()
        }
        body?.apply {
            setTextColor(Color.parseColor(config.body_color))
            textSize = config.body_fontSize.toFloat()
        }
        warning?.apply {
            setTextColor(Color.parseColor(config.warning_color))
            textSize = config.warning_fontSize.toFloat()
        }
        sponsored?.apply {
            setTextColor(Color.parseColor(config.sponsored_color))
            textSize = config.sponsored_fontSize.toFloat()
        }
        age?.apply {
            textSize = config.age_fontSize.toFloat()
            setTextColor(Color.parseColor(config.age_color))
        }
        domain?.apply {
            setTextColor(Color.parseColor(config.domain_color))
            textSize = config.domain_fontSize.toFloat()
        }
        setBackgroundColor(Color.TRANSPARENT)
        media?.visibility = VISIBLE
    }

    private fun requestAd(id: String, width: Int, height: Int) {
        // Setup frame for Ad Native block
        // Request Ad...
        val adRequest = NativeAdRequestConfiguration.Builder(id)
            .setShouldLoadImagesAutomatically(true)
            .build()
        // Load native Ad
        if (
            title !== null &&
            icon !== null &&
            body !== null &&
            media !== null &&
            warning !== null &&
            feedback !== null &&
            sponsored !== null
        ) {
            adLoader.loadAd(adRequest)
        } else {
            log("A native ad block must contain children inside it")
        }

    }

    private inner class LoaderListener : NativeAdLoadListener {
        override fun onAdLoaded(p0: NativeAd) {
            p0.setNativeAdEventListener(EventListener())
            // Build native Ad block
            val adBinder = NativeAdViewBinder.Builder(adView)
                .apply {
                    if (age !== null) {
                        setAgeView(age)
                    }
                    if (domain !== null) {
                        setDomainView(domain)
                    }
                    if (favicon !== null) {
                        setFaviconView(favicon)
                    }

                    setTitleView(title)//********** ! REQUIRED ! 1
                    setIconView(icon)//************ ! REQUIRED ! 2
                    setBodyView(body)//************ ! REQUIRED ! 3
                    setMediaView(media)//********** ! REQUIRED ! 4
                    setWarningView(warning)//****** ! REQUIRED ! 5
                    setFeedbackView(feedback)//**** ! REQUIRED ! 6
                    setSponsoredView(sponsored)//** ! REQUIRED ! 7

                }.build()
            p0.bindNativeAd(adBinder)
            setupFrame()
            requestLayout()
            log("Success viewed")
            onReceiveNativeEvent()
        }

        override fun onAdFailedToLoad(p0: AdRequestError) {
            Log.d("RNYandexAdsMobile", "onAdFailedToLoad")
            Log.d("RNYandexAdsMobile", "code: " + p0.code)
            Log.d("RNYandexAdsMobile", "message: " + p0.description)
        }

    }

    private fun toPx(value: Int): Int {
        return PixelUtil.toPixelFromDIP(value.toDouble()).toInt()
    }

    private fun log(value: String?) {
        if (value !== null) {
            Log.d("RNYandexAdsMobile", value)
        }
    }


    fun onReceiveNativeEvent() {
        val event = Arguments.createMap()
        event.putString("message", "MyMessage")
        reactContext
            .getJSModule(RCTEventEmitter::class.java)
            .receiveEvent(adView.id, "onLoadEnd", event)
    }

    private inner class EventListener : NativeAdEventListener {
        override fun onAdClicked() {
            log("onAdClicked")
        }

        override fun onLeftApplication() {
            log("onLeftApplication")
        }

        override fun onReturnedToApplication() {
            log("onReturnedToApplication")
        }

        override fun onImpression(p0: ImpressionData?) {
            log("Success impression")
        }
    }
}
