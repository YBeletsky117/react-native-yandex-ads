package com.reactnativeyandexads.utils

class NativeAdConfig {
    var title_color: String = "#000000"
    var title_fontSize: Int = 1

    var body_color: String = "#000000"
    var body_fontSize: Int = 1

    var warning_color: String = "#000000"
    var warning_fontSize: Int = 1

    var sponsored_color: String = "#000000"
    var sponsored_fontSize: Int = 1

    var age_color: String = "#000000"
    var age_fontSize: Int = 1

    var domain_color: String = "#000000"
    var domain_fontSize: Int = 1

    var media_width: Int = 1
    var media_height: Int = 1
}

class NativeAdNativeIDs {
    var view: Int = 0
    var title: String = ""
    var body: String = ""
    var warning: String = ""
    var sponsored: String = ""
    var age: String = ""
    var domain: String = ""
    var icon: String = ""
    var favicon: String = ""
    var feedback: String = ""
    var media: String = ""
}


object Res {
  // Main package name
  const val MAIN_REACT_CLASS = "YaAdsMobileModule"
  // Yandex Interstitial Ads
  object Interstitial {
    const val REACT_CLASS = "YaAdInterstitialModule"
  }
  // Yandex Rewarded Ads
  object Rewarded {
    const val REACT_CLASS = "YaAdRewardedModule"
  }
  // Yandex Banner Ads
  object Banner {
    const val REACT_CLASS = "YaAdBannerViewManager"
  }
  // Yandex Text Ads view
  object Text {
    const val REACT_CLASS = "YaAdTextViewManager"
  }
  // Yandex Image Ads view
  object Image {
    const val REACT_CLASS = "YaAdImageViewManager"
  }
  // Yandex Media Ads view
  object Media {
    const val REACT_CLASS = "YaAdMediaViewManager"
  }
  // Yandex Container Ads view
  object Container {
    const val REACT_CLASS = "YaAdContainerViewManager"
  }
  // Yandex Native Ads view
  object Native {
    const val REACT_CLASS = "YaAdNativeViewManager"
  }
}
