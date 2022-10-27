//
//  RNYandexAdsMobileNativeManager.swift
//  react-native-ya-ads-mobile
//
//  Created by Nik Eroon on 30.08.2022.
//

import Foundation


@objc(YaAdNativeViewManager)
class YaAdNativeViewManager : RCTViewManager {
    
    override func view() -> UIView! {
        return YaAdNativeView()
    }
    
    override static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
}
