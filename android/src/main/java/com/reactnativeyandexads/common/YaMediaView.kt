package com.reactnativeyandexads.common

import android.view.Choreographer
import android.view.View
import android.widget.FrameLayout
import com.facebook.react.uimanager.PixelUtil
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactPropGroup
import com.reactnativeyandexads.utils.Res.Media.REACT_CLASS
import com.yandex.mobile.ads.nativeads.MediaView

class YaMediaView: SimpleViewManager<MediaView>() {
    override fun getName() = REACT_CLASS
    private var propWidth: Int? = null
    private var propHeight: Int? = null


    override fun createViewInstance(reactContext: ThemedReactContext): MediaView {
        return createMediaView(reactContext)
    }

    private fun createMediaView(reactContext: ThemedReactContext): MediaView {
        val mediaView = MediaView(reactContext)
        setupLayout(mediaView)
        return mediaView
    }

    private fun setupLayout(view: View) {
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                manuallyLayoutChildren(view)
                view.viewTreeObserver.dispatchOnGlobalLayout()
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }

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

    @ReactPropGroup(names = ["width", "height"], customType = "Style")
    fun setStyle(view: FrameLayout, index: Int, value: Int) {
        if (index == 0) propWidth = toPx(value)
        if (index == 1) propHeight = toPx(value)
    }
    private fun toPx(value: Int): Int {
        return PixelUtil.toPixelFromDIP(value.toDouble()).toInt()
    }
}
