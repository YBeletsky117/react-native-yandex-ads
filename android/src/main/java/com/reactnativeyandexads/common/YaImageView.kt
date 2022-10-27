package com.reactnativeyandexads.common

import android.widget.ImageView
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.reactnativeyandexads.utils.Res.Image.REACT_CLASS

class YaImageView: SimpleViewManager<ImageView>() {
    override fun getName() = REACT_CLASS
    override fun createViewInstance(reactContext: ThemedReactContext): ImageView {
        return ImageView(reactContext)
    }
}
