package com.reactnativeyandexads.native

import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.Choreographer
import android.view.View
import android.view.ViewGroup
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.uimanager.annotations.ReactPropGroup
import com.reactnativeyandexads.utils.NativeAdConfig
import com.reactnativeyandexads.utils.NativeAdNativeIDs
import com.reactnativeyandexads.utils.Res.Native.REACT_CLASS
import com.yandex.mobile.ads.nativeads.NativeAdView


class YaNativeViewManager(
    private val reactContext: ReactApplicationContext
) : ViewGroupManager<NativeAdView>() {
    private var propWidth: Int? = null
    private var propHeight: Int? = null
    private var adUnitId: String? = null

    private var nativeAdConfig: NativeAdConfig = NativeAdConfig()
    private var nativeIDs: NativeAdNativeIDs = NativeAdNativeIDs()


    override fun getName() = REACT_CLASS

    /**
     * Return a NativeAdView which will later hold the Fragment
     */
    override fun createViewInstance(reactContext: ThemedReactContext) = NativeAdView(reactContext)

    /**
     * Map the "create" command to an integer
     */
    override fun getCommandsMap() = mapOf("create" to COMMAND_CREATE)

    /**
     * Handle "create" command (called from JS) and call createFragment method
     */
    override fun receiveCommand(
        root: NativeAdView,
        commandId: String,
        args: ReadableArray?
    ) {
        super.receiveCommand(root, commandId, args)
        nativeIDs.view = requireNotNull(args).getInt(0)

        nativeIDs.title = requireNotNull(args).getString(1)
        nativeIDs.body = requireNotNull(args).getString(2)
        nativeIDs.warning = requireNotNull(args).getString(3)
        nativeIDs.sponsored = requireNotNull(args).getString(4)
        nativeIDs.age = requireNotNull(args).getString(5)
        nativeIDs.domain = requireNotNull(args).getString(6)
        nativeIDs.icon = requireNotNull(args).getString(7)
        nativeIDs.favicon = requireNotNull(args).getString(8)
        nativeIDs.feedback = requireNotNull(args).getString(9)
        nativeIDs.media = requireNotNull(args).getString(10)

        when (commandId.toInt()) {
            COMMAND_CREATE -> createFragment(root)
        }
    }

    @ReactProp(name = "adUnitId")
    fun setAdUnitId(view: NativeAdView, value: String?) {
        adUnitId = value
    }

    @ReactPropGroup(
        names = [
            "titleFontSize",
            "bodyFontSize",
            "warningFontSize",
            "sponsoredFontSize",
            "ageFontSize",
            "domainFontSize",
            "mediaWidth",
            "mediaHeight",
        ]
    )
    fun setConfigFontSizes(view: NativeAdView, index: Int, value: Int) {
        if (index == 0) nativeAdConfig.title_fontSize = value
        if (index == 1) nativeAdConfig.body_fontSize = value
        if (index == 2) nativeAdConfig.warning_fontSize = value
        if (index == 3) nativeAdConfig.sponsored_fontSize = value
        if (index == 4) nativeAdConfig.age_fontSize = value
        if (index == 5) nativeAdConfig.domain_fontSize = value
        if (index == 6) nativeAdConfig.media_width = value
        if (index == 7) nativeAdConfig.media_height = value
    }

    @ReactProp(name = "titleColor")
    fun setTitleColor(view: NativeAdView, value: String) {
        nativeAdConfig.title_color = value
    }

    @ReactProp(name = "bodyColor")
    fun setBodyColor(view: NativeAdView, value: String) {
        nativeAdConfig.body_color = value
    }

    @ReactProp(name = "warningColor")
    fun setWarningColor(view: NativeAdView, value: String) {
        nativeAdConfig.warning_color = value
    }

    @ReactProp(name = "sponsoredColor")
    fun setSponsoredColor(view: NativeAdView, value: String) {
        nativeAdConfig.sponsored_color = value
    }

    @ReactProp(name = "ageColor")
    fun setAgeColor(view: NativeAdView, value: String) {
        nativeAdConfig.age_color = value
    }

    @ReactProp(name = "domainColor")
    fun setDomainColor(view: NativeAdView, value: String) {
        nativeAdConfig.domain_color = value
    }


    @ReactPropGroup(names = ["width", "height"], customType = "Style")
    fun setStyle(view: NativeAdView, index: Int, value: Int) {
        if (index == 0) propWidth = value
        if (index == 1) propHeight = value
    }

    /**
     * Replace your React Native view with a custom fragment
     */
    fun createFragment(root: NativeAdView) {
        val parentView = root.findViewById<ViewGroup>(nativeIDs.view)
        setupLayout(parentView)

        val adId = requireNotNull(adUnitId)
        val width = requireNotNull(propWidth)
        val height = requireNotNull(propHeight)

//        nativeAdView.addView(parentView, width, height )
//        removeViewAt(root, 0)
//        rom
//        val activity = reactContext.currentActivity as Activity
//        activity.setContentView(parentView, ViewGroup.LayoutParams(width, height))
        if (adUnitId !== null && propHeight !== null && propWidth !== null) {
            YaNativeTemplateView(
                reactContext,
                root,
                adId,
                width,
                height,
                nativeIDs,
                nativeAdConfig,
            )
        }
    }

    fun setupLayout(view: View) {
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                manuallyLayoutChildren(view)
                view.viewTreeObserver.dispatchOnGlobalLayout()
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }

    /**
     * Layout all children properly
     */
    private fun manuallyLayoutChildren(view: View) {
        // propWidth and propHeight coming from react-native props
        val width = requireNotNull(propWidth)
        val height = requireNotNull(propHeight)

        view.measure(
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        )

        view.layout(0, 0, width, height)
    }

    private fun dp2px(dp: Int, dm: DisplayMetrics): Int {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), dm))
    }

    private fun log(value: String?) {
        if (value !== null) {
            Log.d("RNYandexAdsMobile", value)
        }
    }

    override fun getExportedCustomBubblingEventTypeConstants(): Map<String, Any>? {
        return mapOf(
            "onLoadEnd" to mapOf(
                "phasedRegistrationNames" to mapOf("bubbled" to "onLoadEnd")
            )
        )
    }

//    override fun getExportedCustomBubblingEventTypeConstants(): Map<String, Any> {
//        return mapOf(
//            "onLoadEnd" to mapOf(
//                "phasedRegistrationNames" to mapOf(
//                    "bubbled" to "onLoadEnd"
//                )
//            )
//        )
//    }

    companion object {
        private const val COMMAND_CREATE = 1
    }
}
