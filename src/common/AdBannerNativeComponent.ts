import { HostComponent, requireNativeComponent, ViewProps } from 'react-native'
import { cacheNativeView, getCachedNativeView } from '../utils'
import type { AdBannerType } from '../resources'
import RES from '../resources'

export type NativeEventType = {
  nativeEvent: {
    adUnitID: string | null
  }
}

export type NativeImpressionEventType = {
  nativeEvent: {
    adUnitID: string | null
    data?: string
  }
}

export type NativeFailedEventType = {
  nativeEvent: {
    adUnitID: string | null
    errorMessage?: string
  }
}

type AdBannerComponentType = {
  adUnitID: string
  customSize?: [number, number]
  size?: AdBannerType
  style: ViewProps['style']
  place?: 'top' | 'botttom'
  requestParams?: Record<string, string>
  onDidLoad: (event: NativeEventType) => void
  onClick: (event: NativeEventType) => void
  onDidTrackImpression: (event: NativeImpressionEventType) => void
  onDidFailLoading: (event: NativeFailedEventType) => void
  onWillLeaveApp: (event: NativeEventType) => void
  onWillPresent?: (event: NativeEventType) => void
  onDidDismiss?: (event: NativeEventType) => void
  onDidReturnedToApplication?: (event: NativeEventType) => void
}

let AdBannerComponent: HostComponent<AdBannerComponentType>
const globalView = getCachedNativeView<typeof AdBannerComponent>()

if (__DEV__ && !globalView) {
  AdBannerComponent = requireNativeComponent<AdBannerComponentType>(
    RES.VIEW_MANAGERS.BANNER
  )
  cacheNativeView<typeof AdBannerComponent>(AdBannerComponent)
} else if (__DEV__ && getCachedNativeView<typeof AdBannerComponent>()) {
  AdBannerComponent = getCachedNativeView<typeof AdBannerComponent>()
} else {
  AdBannerComponent = requireNativeComponent<AdBannerComponentType>(
    RES.VIEW_MANAGERS.BANNER
  )
}

export default AdBannerComponent
