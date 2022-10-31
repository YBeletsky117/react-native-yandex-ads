//
//  RNYandexAdsMobileBanner.swift
//  react-native-ya-ads-mobile
//
//  Created by Nik Eroon on 29.08.2022.
//

import SwiftUI
import YandexMobileAds

class YaAdBannerView: UIView {
    var adView: YMAAdView!

    @objc var place: NSString = "top"

    @objc var adUnitID: String? {
        didSet {
            self.setupView()
        }
    }
    @objc var size: NSString? {
        didSet {
            self.setupView()
        }
    }

    @objc var onDidLoad: RCTDirectEventBlock?
    @objc var onClick: RCTBubblingEventBlock?
    @objc var onDidTrackImpression: RCTDirectEventBlock?
    @objc var onDidFailLoading: RCTDirectEventBlock?
    @objc var onWillLeaveApp: RCTDirectEventBlock?
    @objc var onWillPresent: RCTBubblingEventBlock?
    @objc var onDidDismiss: RCTBubblingEventBlock?
    @objc var requestParams: NSDictionary?

    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }

    func setupView () {
        DispatchQueue.main.async {
            guard let adUnitID = self.adUnitID else { return }
            guard let size = self.size else { return }

            let adSize = YMAAdSize.flexibleSize(with: self.getSize(size))
            self.adView = YMAAdView(adUnitID: adUnitID, adSize: adSize)
            self.adView.delegate = self

            self.adView.removeFromSuperview()
            if self.requestParams != nil && self.requestParams is NSDictionary {
                if let requestParams = self.requestParams {
                    let adRewuest = YMAMutableAdRequest()
                    var params = ["": ""]
                    var haveInvalidArgument = false
                    for (key, value) in requestParams {
                        if (key is String && value is String) {
                            params[key as! String] = (value as! String)
                        } else {
                            haveInvalidArgument = true
                        }
                    }
                    if (!haveInvalidArgument) {
                        adRewuest.parameters = params
                        self.adView.loadAd(with: adRewuest)
                        return;
                    }
                }
            } else {
                self.adView.loadAd()
                return;
            }
        }
    }

    private func getSize(_ size: NSString?) -> CGSize {
        if size == "BANNER_300x250" {
            return CGSize(width: 300, height: 250)
        } else if size == "BANNER_300x300" {
            return CGSize(width: 300, height: 300)
        } else if size == "BANNER_320x250" {
            return CGSize(width: 320, height: 250)
        } else if size == "BANNER_320x50" {
            return CGSize(width: 320, height: 50)
        } else if size == "BANNER_320x100" {
            return CGSize(width: 320, height: 1200)
        } else if size == "BANNER_300x250" {
            return CGSize(width: 300, height: 250)
        } else if size == "BANNER_400x240" {
            return CGSize(width: 400, height: 240)
        } else if size == "BANNER_728x90" {
            return CGSize(width: 728, height: 90)
        }

        return CGSize(width: 240, height: 400)
    }

}

extension YaAdBannerView: YMAAdViewDelegate {

    func adViewDidLoad(_ adView: YMAAdView) {
        if self.place == "top" {
            self.adView.displayAtTop(in: self as UIView)
        } else {
            self.adView.displayAtBottom(in: self as UIView)
        }
        guard let onDidLoad = self.onDidLoad else { return }
        onDidLoad(["adUnitID": adView.adUnitID])
    }

    func adViewDidClick(_ adView: YMAAdView) {
        guard let onClick = self.onClick else { return }
        onClick(["adUnitID": adView.adUnitID])
    }

    func adView(_ adView: YMAAdView, didTrackImpressionWith impressionData: YMAImpressionData?) {
        guard let onDidTrackImpression = self.onDidTrackImpression else { return }
        onDidTrackImpression(["adUnitID": adView.adUnitID, "data": impressionData?.description])
    }

    func adViewDidFailLoading(_ adView: YMAAdView, error: Error) {
        guard let onDidFailLoading = self.onDidFailLoading else { return }
        onDidFailLoading(["adUnitID": adView.adUnitID, "errorMessage": error.localizedDescription])
    }

    func adViewWillLeaveApplication(_ adView: YMAAdView) {
        guard let onWillLeaveApp = self.onWillLeaveApp else { return }
        onWillLeaveApp(["adUnitID": adView.adUnitID])
    }

    func adView(_ adView: YMAAdView, willPresentScreen viewController: UIViewController?) {
        guard let onWillPresent = self.onWillPresent else { return }
        onWillPresent(["adUnitID": adView.adUnitID])
    }

    func adView(_ adView: YMAAdView, didDismissScreen viewController: UIViewController?) {
        guard let onDidDismiss = self.onDidDismiss else { return }
        onDidDismiss(["adUnitID": adView.adUnitID])
    }

}
