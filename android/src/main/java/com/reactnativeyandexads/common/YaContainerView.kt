package com.reactnativeyandexads.common

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.facebook.react.uimanager.PixelUtil
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.reactnativeyandexads.utils.Res.Container.REACT_CLASS

class YaContainerView: ViewGroupManager<ConstraintLayout>() {
    override fun getName() = REACT_CLASS

    override fun createViewInstance(reactContext: ThemedReactContext): ConstraintLayout {
        val ley = ConstraintLayout(reactContext)
        val w = toPx(310)
        val h = toPx(117)
        ley.measure(
            View.MeasureSpec.makeMeasureSpec(w, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(h, View.MeasureSpec.EXACTLY)
        )
        val p = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        ley.layout(0,0, w, h,)
//        ley.addView(reactContext)
        return ley
    }
    private fun toPx(value: Int): Int {
        return PixelUtil.toPixelFromDIP(value.toDouble()).toInt()
    }
}
