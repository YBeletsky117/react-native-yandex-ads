//
//  RCTYandexAdsMobileRewarded.swift
//  react-native-ya-ads-mobile
//
//  Created by Nik Eroon on 03.09.2022.
//

import Foundation
import YandexMobileAds
import React

@objc (YaAdRewardedModule)
class YaAdRewardedModule: RCTEventEmitter {
    var rewardedAd: YMARewardedAd?
    
    private var _resolve: RCTPromiseResolveBlock? = nil
    private var _reject: RCTPromiseRejectBlock? = nil
    
    @objc
    func showRewardedAd(_ adUnitID: String, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) -> Void {
            self._resolve = resolve
            self._reject = reject
            self.rewardedAd = YMARewardedAd(adUnitID: adUnitID)
            self.rewardedAd?.delegate = self
            self.rewardedAd?.load()
    }
    
    override func supportedEvents() -> [String]! {
        return RewardEvents.allCases.map { $0.rawValue }
    }
    
    
    override static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
}

// MARK: - YMARewardedAdDelegate

extension YaAdRewardedModule: YMARewardedAdDelegate {
    
    func rewardedAd(_ rewardedAd: YMARewardedAd, didReward reward: YMAReward) {
        sendEvent(withName: RewardEvents.onRewardedAdDidReward.rawValue, body: [
            "adUnitID": rewardedAd.adUnitID,
            "amount": reward.amount,
            "type": reward.type,
        ])
    }
    
    func rewardedAdDidLoad(_ rewardedAd: YMARewardedAd) {
        DispatchQueue.main.async {
            self._resolve!(true)
            self.rewardedAd?.present(from: RCTPresentedViewController()!)
        }
        sendEvent(withName: RewardEvents.onRewardedAdDidLoad.rawValue, body: [
            "adUnitID": rewardedAd.adUnitID,
        ])
    }

    func rewardedAdDidFail(toLoad rewardedAd: YMARewardedAd, error: Error) {
        DispatchQueue.main.async {
            self._reject!("YandexAdsMobile: Rewarded", error.localizedDescription, error)
        }
        sendEvent(withName: RewardEvents.onRewardedAdDidFail.rawValue, body: [
            "adUnitID": rewardedAd.adUnitID,
            "error": error.localizedDescription
        ])
    }

    func rewardedAdDidClick(_ rewardedAd: YMARewardedAd) {
        sendEvent(withName: RewardEvents.onRewardedAdDidClick.rawValue, body: ["adUnitID" : rewardedAd.adUnitID])
    }

    func rewardedAd(_ rewardedAd: YMARewardedAd, didTrackImpressionWith impressionData: YMAImpressionData?) {
        sendEvent(withName: RewardEvents.onRewardedAdImpressionTracked.rawValue, body: ["adUnitID": rewardedAd.adUnitID, "impressionData": impressionData as Any])
    }

    func rewardedAdWillLeaveApplication(_ rewardedAd: YMARewardedAd) {
        sendEvent(withName: RewardEvents.onRewardedAdWillLeaveApplication.rawValue, body: ["adUnitID": rewardedAd.adUnitID])
    }

    func rewardedAdDidFail(toPresent rewardedAd: YMARewardedAd, error: Error) {
        _reject!("YandexAdsMobile", "InterstitialAdError", error)
        sendEvent(withName: RewardEvents.onRewardedAdDidFail.rawValue, body: ["adUnitID": rewardedAd.adUnitID, "error": error.localizedDescription])
    }

    func rewardedAdWillAppear(_ rewardedAd: YMARewardedAd) {
        sendEvent(withName: RewardEvents.onRewardedAdWillAppear.rawValue, body: ["adUnitID": rewardedAd.adUnitID])
    }

    func rewardedAdDidAppear(_ rewardedAd: YMARewardedAd) {
        sendEvent(withName: RewardEvents.onRewardedAdDidAppear.rawValue, body: ["adUnitID": rewardedAd.adUnitID])
    }

    func rewardedAdWillDisappear(_ rewardedAd: YMARewardedAd) {
        sendEvent(withName: RewardEvents.onRewardedAdWillDisappear.rawValue, body: ["adUnitID": rewardedAd.adUnitID])
    }

    func rewardedAdDidDisappear(_ rewardedAd: YMARewardedAd) {
        sendEvent(withName: RewardEvents.onRewardedAdDidDisappear.rawValue, body: ["adUnitID": rewardedAd.adUnitID])
    }

    func rewardedAd(_ rewardedAd: YMARewardedAd, willPresentScreen viewController: UIViewController?) {
        sendEvent(withName: RewardEvents.onRewardedAdWillPresent.rawValue, body: ["adUnitID": rewardedAd.adUnitID])
    }
}


enum RewardEvents: String, CaseIterable {
    case onRewardedAdDidReward = "onRewardedAdDidReward"
    case onRewardedAdDidLoad = "onRewardedAdDidLoad"
    case onRewardedAdDidFail = "onRewardedAdDidFail"
    case onRewardedAdDidClick = "onRewardedAdDidClick"
    case onRewardedAdImpressionTracked = "onRewardedAdImpressionTracked"
    case onRewardedAdWillLeaveApplication = "onRewardedAdWillLeaveApplication"
    case onRewardedAdWillAppear = "onRewardedAdWillAppear"
    case onRewardedAdDidAppear = "onRewardedAdDidAppear"
    case onRewardedAdWillDisappear = "onRewardedAdWillDisappear"
    case onRewardedAdDidDisappear = "onRewardedAdDidDisappear"
    case onRewardedAdWillPresent = "onRewardedAdWillPresent"
}
