//
//  RNYandexAdsMobileInterstitial\.m
//  react-native-ya-ads-mobile
//
//  Created by Nik Eroon on 29.08.2022.
//

#import <Foundation/Foundation.h>
#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h"

@interface RCT_EXTERN_MODULE(YaAdInterstitialModule, RCTEventEmitter)

RCT_EXTERN_METHOD(showInterstitialAd: (NSString)adUnitID resolve:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)

@end
