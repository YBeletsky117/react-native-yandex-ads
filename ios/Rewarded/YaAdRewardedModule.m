//
//  RCTYandexAdsMobileRewarded.m
//  react-native-ya-ads-mobile
//
//  Created by Nik Eroon on 03.09.2022.
//

#import <Foundation/Foundation.h>
#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h"

@interface RCT_EXTERN_MODULE(YaAdRewardedModule, RCTEventEmitter)

RCT_EXTERN_METHOD(showRewardedAd: (NSString)adUnitID resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)

@end
