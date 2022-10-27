//
//  RNYandexAdsMobile.swift
//  RNYandexAdsMobile
//
//  Created by Nik Eroon on 23.08.2022.
//  Copyright © 2022 Facebook. All rights reserved.
//

import Foundation
import YandexMobileAds

@objc (YaAdInterstitialModule)
class YaAdInterstitialModule : RCTEventEmitter {
    var interstitialAd: YMAInterstitialAd!
    
    private var _resolve: RCTPromiseResolveBlock? = nil
    private var _reject: RCTPromiseRejectBlock? = nil
    
    @objc
    func showInterstitialAd(_ adUnitID: String, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) -> Void {
            self._resolve = resolve
            self._reject = reject
            self.interstitialAd = YMAInterstitialAd(adUnitID: adUnitID)
            self.interstitialAd.delegate = self
            self.interstitialAd.load()
    }
    
    override func supportedEvents() -> [String]! {
        return InterstitialEvents.allCases.map { $0.rawValue }
    }
    
    
    override static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
}


extension YaAdInterstitialModule: YMAInterstitialAdDelegate {
    func interstitialAdDidLoad(_ interstitialAd: YMAInterstitialAd) {
        DispatchQueue.main.async {
            self._resolve!("true")
            self.interstitialAd.present(from: RCTPresentedViewController()!)
        }
        sendEvent(withName: InterstitialEvents.onInterstitialAdDidLoad.rawValue, body: ["adUnitID": interstitialAd.adUnitID])
    }

    func interstitialAdDidFail(toLoad interstitialAd: YMAInterstitialAd, error: Error) {
        DispatchQueue.main.async {
            self._reject!("YandexAdsMobile: Interstitial", error.localizedDescription, error)
        }
        sendEvent(withName: InterstitialEvents.onInterstitialAdDidFail.rawValue, body: ["adUnitID": interstitialAd.adUnitID, "error": error.localizedDescription])
    }

    func interstitialAdDidClick(_ interstitialAd: YMAInterstitialAd) {
        sendEvent(withName: InterstitialEvents.onInterstitialAdDidClick.rawValue, body: ["adUnitID": interstitialAd.adUnitID])
    }

    func interstitialAd(_ interstitialAd: YMAInterstitialAd, didTrackImpressionWith impressionData: YMAImpressionData?) {
        sendEvent(withName: InterstitialEvents.onInterstitialAdImpressionTracked.rawValue, body: ["adUnitID": interstitialAd.adUnitID, "impressionData": impressionData as Any])
    }

    func interstitialAdWillLeaveApplication(_ interstitialAd: YMAInterstitialAd) {
        sendEvent(withName: InterstitialEvents.onInterstitialAdWillLeaveApplication.rawValue, body: ["adUnitID": interstitialAd.adUnitID])
    }

    func interstitialAdDidFail(toPresent interstitialAd: YMAInterstitialAd, error: Error) {
        self._reject!("YandexAdsMobile: InterstitialA", error.localizedDescription, error)
        sendEvent(withName: InterstitialEvents.onInterstitialAdDidFail.rawValue, body: ["adUnitID": interstitialAd.adUnitID, "error": error.localizedDescription])
    }

    func interstitialAdWillAppear(_ interstitialAd: YMAInterstitialAd) {
        sendEvent(withName:  InterstitialEvents.onInterstitialAdWillAppear.rawValue, body: ["adUnitID": interstitialAd.adUnitID])
    }

    func interstitialAdDidAppear(_ interstitialAd: YMAInterstitialAd) {
        sendEvent(withName: InterstitialEvents.onInterstitialAdDidAppear.rawValue, body: ["adUnitID": interstitialAd.adUnitID])
    }

    func interstitialAdWillDisappear(_ interstitialAd: YMAInterstitialAd) {
        sendEvent(withName: InterstitialEvents.onInterstitialAdWillDisappear.rawValue, body: ["adUnitID": interstitialAd.adUnitID])
    }

    func interstitialAdDidDisappear(_ interstitialAd: YMAInterstitialAd) {
        sendEvent(withName: InterstitialEvents.onInterstitialAdDidDisappear.rawValue, body: ["adUnitID": interstitialAd.adUnitID])
    }

    func interstitialAd(_ interstitialAd: YMAInterstitialAd, willPresentScreen webBrowser: UIViewController?) {
        sendEvent(withName: InterstitialEvents.onInterstitialAdWillPresent.rawValue, body: ["adUnitID": interstitialAd.adUnitID])
    }
}

enum InterstitialEvents: String, CaseIterable {
    case onInterstitialAdDidLoad = "onInterstitialAdDidLoad"
    case onInterstitialAdDidFail = "onInterstitialAdDidFail"
    case onInterstitialAdDidClick = "onInterstitialAdDidClick"
    case onInterstitialAdImpressionTracked = "onInterstitialAdImpressionTracked"
    case onInterstitialAdWillLeaveApplication = "onInterstitialAdWillLeaveApplication"
    case onInterstitialAdWillAppear = "onInterstitialAdWillAppear"
    case onInterstitialAdDidAppear = "onInterstitialAdDidAppear"
    case onInterstitialAdWillDisappear = "onInterstitialAdWillDisappear"
    case onInterstitialAdDidDisappear = "onInterstitialAdDidDisappear"
    case onInterstitialAdWillPresent = "onInterstitialAdWillPresent"
}
