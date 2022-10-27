package com.reactnativeyandexads.common

import android.widget.TextView
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.reactnativeyandexads.utils.Res.Text.REACT_CLASS
// TODO
class YaTextView: SimpleViewManager<TextView>() {
    override fun getName() = REACT_CLASS
    override fun createViewInstance(reactContext: ThemedReactContext): TextView {
        return TextView(reactContext)
    }
}
