//
//  RNYandexAdsMobileNativeManager.m
//  react-native-ya-ads-mobile
//
//  Created by Nik Eroon on 30.08.2022.
//

#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"
#import <React/RCTBridgeModule.h>


@interface RCT_EXTERN_MODULE(YaAdNativeViewManager, RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(adUnitId, NSString)
RCT_EXPORT_VIEW_PROPERTY(width, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(height, NSNumber)

RCT_EXPORT_VIEW_PROPERTY(onWillLoad, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDidLoad, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDidTrackImpression, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onWillLeaveApp, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDidFailLoading, RCTDirectEventBlock)

RCT_EXPORT_VIEW_PROPERTY(onClick, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onWillPresent, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onDidDismiss, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onClose, RCTBubblingEventBlock)

@end
