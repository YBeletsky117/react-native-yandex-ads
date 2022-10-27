import { initialize } from './utils'

export { default as YaRewardedAds } from './rewarded'
export { default as YaInterstitialAds } from './interstitial'
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
