import { PixelRatio } from 'react-native'

export const YA_NATIVE_COMPONENTS = {
  container: 'AdContainer',
  title: 'AdTitle',
  body: 'AdBody',
  warning: 'AdWarning',
  sponsored: 'AdSponsored',
  age: 'AdAge',
  domain: 'AdDomain',
  icon: 'AdIcon',
  favicon: 'AdFavicon',
  feedback: 'AdFeedback',
  media: 'AdMedia'
}
export const YA_NATIVE_ID = {
  container: 'YandexAdsNativeContainer',
  title: 'YandexAdsNativeTitle',
  body: 'YandexAdsNativeBody',
  warning: 'YandexAdsNativeWarning',
  sponsored: 'YandexAdsNativeSponsored',
  age: 'YandexAdsNativeAge',
  domain: 'YandexAdsNativeDomain',
  icon: 'YandexAdsNativeIcon',
  favicon: 'YandexAdsNativeFavicon',
  feedback: 'YandexAdsNativeFeedback',
  media: 'YandexAdsNativeMedia'
}
export const DEFAULT_CONFIG = {
  container: {},
  title: {
    color: '#FFFFFF',
    fontSize: 12,
    numberOfLines: 1
  },
  body: {
    color: '#21F5FF',
    fontSize: 12,
    numberOfLines: 1
  },
  warning: {
    color: '#6F6F1F',
    fontSize: 12,
    numberOfLines: 1
  },
  sponsored: {
    color: '#FF545F',
    fontSize: 12,
    numberOfLines: 1
  },
  age: {
    color: '#FF1FFF',
    fontSize: 12,
    numberOfLines: 1
  },
  domain: {
    color: '#3FF22F',
    fontSize: 12,
    numberOfLines: 1
  },
  icon: {
    width: PixelRatio.getPixelSizeForLayoutSize(10),
    height: PixelRatio.getPixelSizeForLayoutSize(10)
  },
  favicon: {
    width: PixelRatio.getPixelSizeForLayoutSize(10),
    height: PixelRatio.getPixelSizeForLayoutSize(10)
  },
  feedback: {
    width: PixelRatio.getPixelSizeForLayoutSize(10),
    height: PixelRatio.getPixelSizeForLayoutSize(10)
  },
  media: {
    width: 12,
    height: 12
  }
}
export const REQUIRED_NATIVE_AD_COMPONENTS = [
  YA_NATIVE_COMPONENTS.title,
  YA_NATIVE_COMPONENTS.icon,
  YA_NATIVE_COMPONENTS.body,
  YA_NATIVE_COMPONENTS.media,
  YA_NATIVE_COMPONENTS.warning,
  YA_NATIVE_COMPONENTS.feedback,
  YA_NATIVE_COMPONENTS.sponsored
]
