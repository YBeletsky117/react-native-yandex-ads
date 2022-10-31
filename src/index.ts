import { initialize } from './utils'

export { default as AdRewarded } from './rewarded'
export { default as AdInterstitial } from './interstitial'
export { default as AdBanner } from './banner'
export { InterstitialEventTypes, RewardedEventTypes } from './resources'
export {
  AdBannerType,
  YA_NATIVE_COMPONENTS as NativeAdTypes
} from './resources'
// @ts-ignore
import { AdNative as AdContainer } from './native'
import { AdMedia, AdImage, AdText, AdContainer as AdView } from './components'

export const AdNative = {
  Container: AdContainer,
  View: AdView,
  Image: AdImage,
  Media: AdMedia,
  Text: AdText
}

const YandexAds = {
  initialize
}
export default YandexAds
