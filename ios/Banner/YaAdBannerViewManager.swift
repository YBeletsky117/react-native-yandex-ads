//
//  RNYandexAdsMobileBanner.swift
//  react-native-ya-ads-mobile
//
//  Created by Nik Eroon on 29.08.2022.
//

import Foundation


@objc(YaAdBannerViewManager)
class YaAdBannerViewManager : RCTViewManager {
    
    override func view() -> UIView! {
        return YaAdBannerView()
    }
    
    override static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
}
