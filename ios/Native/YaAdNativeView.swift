//
//  RNYandexAdsMobileBanner.swift
//  react-native-ya-ads-mobile
//
//  Created by Nik Eroon on 29.08.2022.
//

import SwiftUI
import YandexMobileAds
import React


class YaAdNativeView: UIView {
    private var adView: YMANativeBannerView?
    
    var adLoader: YMANativeAdLoader!
    
    @objc var adUnitId: String? {
        didSet {
            setupView()
        }
    }
    @objc var height: NSNumber? {
        didSet {
            setupView()
        }
    }
    @objc var width: NSNumber? {
        didSet {
            setupView()
        }
    }
    
    @objc var onWillLoad: RCTDirectEventBlock?
    @objc var onDidLoad: RCTDirectEventBlock?
    @objc var onClick: RCTBubblingEventBlock?
    @objc var onDidTrackImpression: RCTDirectEventBlock?
    @objc var onWillLeaveApp: RCTDirectEventBlock?
    @objc var onWillPresent: RCTBubblingEventBlock?
    @objc var onDidDismiss: RCTBubblingEventBlock?
    @objc var onClose: RCTBubblingEventBlock?
    @objc var onDidFailLoading: RCTDirectEventBlock?
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        setupView()
    }
    
    
    
    @objc func setupView () {
        DispatchQueue.main.async {
            guard
                let adUnitID = self.adUnitId,
                let height = self.height,
                let width = self.width
            else { return }
            
            self.adView = YMANativeBannerView()
            self.adView?.frame = CGRect(x: 0, y: 0, width: CGFloat(truncating: width), height: CGFloat(truncating: height))
            self.adView?.isHidden = true
            
            self.adLoader = YMANativeAdLoader()
            self.adLoader.delegate = self
            
            let requestConfiguration = YMAMutableNativeAdRequestConfiguration(adUnitID: adUnitID)
            requestConfiguration.parameters = ["preferable-height": String(describing: height), "preferable-width": String(describing: width)]
            guard let onWillLoad = self.onWillLoad else { return }
            onWillLoad(["adUnitID": self.adUnitId!])
            self.adLoader.loadAd(with: requestConfiguration)
            
        }
    }
    
    func addAdView() {
        DispatchQueue.main.async {
            guard let adView = self.adView else { return }
            
            adView.translatesAutoresizingMaskIntoConstraints = true
            self.addSubview(adView)
        }}
    
}

// MARK: - YMANativeAdLoaderDelegate

extension YaAdNativeView: YMANativeAdLoaderDelegate {
    func nativeAdLoader(_ loader: YMANativeAdLoader, didLoad ad: YMANativeAd) {
        guard let adView = self.adView else { return }
        ad.delegate = self
        adView.isHidden = false
        adView.ad = ad
        frame = CGRect(x: 0, y: 0, width: CGFloat(truncating: self.width!), height: CGFloat(truncating: self.height!))
        addAdView()
        guard let onDidLoad = self.onDidLoad else { return }
        onDidLoad(["adUnitID": self.adUnitId!])
    }
    
    func configureView(for ad: YMANativeAd) {
        let assets = ad.adAssets()
        
        if let media = assets.media {
            //you can use the aspect ratio if you need it to determine the size of media view.
            print(String(format: "Media aspect ratio: %.2f", media.aspectRatio))
        }
    }
    
    func nativeAdLoader(_ loader: YMANativeAdLoader, didFailLoadingWithError error: Error) {
        guard let onDidFailLoading = self.onDidFailLoading else { return }
        onDidFailLoading(["adUnitID": self.adUnitId!, "error": error.localizedDescription])
    }
}

// MARK: - YMANativeAdDelegate

extension YaAdNativeView: YMANativeAdDelegate {
    
    func nativeAdDidClick(_ ad: YMANativeAd) {
        print("Yana: Ad clicked")
        guard let onClick = self.onClick else { return }
        onClick(["adUnitID": self.adUnitId!])
    }
    
    func nativeAd(_ ad: YMANativeAd, didTrackImpressionWith impressionData: YMAImpressionData?) {
        print("Yana: Impression tracked")
        guard let onDidTrackImpression = self.onDidTrackImpression else { return }
        onDidTrackImpression([
            "adUnitID": self.adUnitId!,
            "impressionData": impressionData?.description as Any
        ])
    }
    
    func nativeAdWillLeaveApplication(_ ad: YMANativeAd) {
        print("Yana: Will leave application")
        guard let onWillLeaveApp = self.onWillLeaveApp else { return }
        onWillLeaveApp(["adUnitID": self.adUnitId!])
    }
    
    func nativeAd(_ ad: YMANativeAd, willPresentScreen viewController: UIViewController?) {
        print("Yana: Will present screen")
        guard let onWillPresent = self.onWillPresent else { return }
        onWillPresent(["adUnitID": self.adUnitId!])
    }
    
    func nativeAd(_ ad: YMANativeAd, didDismissScreen viewController: UIViewController?) {
        print("Yana: Did dismiss screen")
        guard let onDidDismiss = self.onDidDismiss else { return }
        onDidDismiss(["adUnitID": self.adUnitId!])
    }
    
    func close(_ ad: YMANativeAd) {
        print("Yana: Did close")
        adView?.isHidden = true
        guard let onClose = self.onClose else { return }
        onClose(["adUnitID": self.adUnitId!])
    }
    
}

