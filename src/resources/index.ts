enum RewardedEventTypes {
  didReward = 'onDidReward',
  didLoad = 'onDidLoad',
  didFail = 'onDidFail',
  didClick = 'onDidClick',
  impressionTracked = 'onImpressionTracked',
  leaveApplication = 'onWillLeaveApplication',
  willAppear = 'onWillAppear',
  didAppear = 'onDidAppear',
  willDisappear = 'onWillDisappear',
  didDisappear = 'onDidDisappear',
  willPresent = 'onWillPresent'
}

enum InterstitialEventTypes {
  didLoad = 'onDidLoad',
  didFail = 'onDidFail',
  didClick = 'onDidClick',
  impressionTracked = 'onImpressionTracked',
  leaveApplication = 'onWillLeaveApplication',
  willAppear = 'onWillAppear',
  didAppear = 'onDidAppear',
  willDisappear = 'onWillDisappear',
  didDisappear = 'onDidDisappear',
  willPresent = 'onWillPresent'
}

export enum AdBannerType {
  BANNER_300x250 = 'BANNER_300x250',
  BANNER_320x250 = 'BANNER_320x250',
  BANNER_300x300 = 'BANNER_300x300',
  BANNER_320x50 = 'BANNER_320x50',
  BANNER_320x100 = 'BANNER_320x100',
  BANNER_400x240 = 'BANNER_400x240',
  BANNER_728x90 = 'BANNER_728x90'
}

enum bannerHeight {
  BANNER_300x250 = 250,
  BANNER_320x250 = 250,
  BANNER_300x300 = 300,
  BANNER_320x50 = 50,
  BANNER_320x100 = 100,
  BANNER_400x240 = 240,
  BANNER_728x90 = 90
}

enum bannerWidth {
  BANNER_300x250 = 300,
  BANNER_320x250 = 320,
  BANNER_300x300 = 300,
  BANNER_320x50 = 320,
  BANNER_320x100 = 320,
  BANNER_400x240 = 400,
  BANNER_728x90 = 728
}

const RES = {
  MODULES: {
    MAIN: 'YaAdsMobileModule', // only Android
    INTERSTITIAL: 'YaAdInterstitialModule',
    REWARDED: 'YaAdRewardedModule'
  },
  VIEW_MANAGERS: {
    NATIVE: 'YaAdNativeViewManager',
    CONTAINER: 'YaAdContainerViewManager', // only Android
    MEDIA: 'YaAdMediaViewManager', // only Android
    IMAGE: 'YaAdImageViewManager', // only Android
    TEXT: 'YaAdTextViewManager', // only Android
    BANNER: 'YaAdBannerViewManager'
  },
  RewardedEventTypes,
  bannerWidth,
  bannerHeight,
  AdBannerType,
  InterstitialEventTypes
}
export * from './constants'
export default RES
