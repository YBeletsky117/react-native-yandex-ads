//
//  RNYandexAdsMobileBanner.m
//  react-native-ya-ads-mobile
//
//  Created by Nik Eroon on 29.08.2022.
//

#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"

@interface RCT_EXTERN_MODULE(YaAdBannerViewManager, RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(place, NSString)

RCT_EXPORT_VIEW_PROPERTY(adUnitID, NSString)
RCT_EXPORT_VIEW_PROPERTY(size, NSString)

RCT_EXPORT_VIEW_PROPERTY(onDidLoad, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDidTrackImpression, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDidFailLoading, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onWillLeaveApp, RCTDirectEventBlock)

RCT_EXPORT_VIEW_PROPERTY(onClick, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onWillPresent, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDidDismiss, RCTBubblingEventBlock)

@end
