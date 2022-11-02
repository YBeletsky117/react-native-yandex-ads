import { HostComponent, requireNativeComponent, ViewProps } from 'react-native'
import { cacheNativeView, getCachedNativeView } from '../utils'
// @ts-ignore
import RES from '../resources'

export interface AdNativeProps extends ViewProps {
  adUnitId: string
  height: number
  width: number
  titleColor?: string
  titleFontSize?: number
  bodyColor?: string
  bodyFontSize?: number
  warningColor?: string
  warningFontSize?: number
  sponsoredColor?: string
  sponsoredFontSize?: number
  ageColor?: string
  ageFontSize?: number
  domainColor?: string
  domainFontSize?: number
  mediaWidth?: number
  mediaHeight?: number
  titleLines?: number
  bodyLines?: number
  warningLines?: number
  sponsoredLines?: number
  ageLines?: number
  domainLines?: number
  onLoadEnd: () => any
}

let AdNativeComponent: HostComponent<AdNativeProps>
const globalView = getCachedNativeView<typeof AdNativeComponent>()

if (__DEV__ && !globalView) {
  AdNativeComponent = requireNativeComponent<AdNativeProps>(
    RES.VIEW_MANAGERS.NATIVE
  )
  cacheNativeView<typeof AdNativeComponent>(AdNativeComponent)
} else if (__DEV__ && getCachedNativeView<typeof AdNativeComponent>()) {
  AdNativeComponent = getCachedNativeView<typeof AdNativeComponent>()
} else {
  AdNativeComponent = requireNativeComponent<AdNativeProps>(
    RES.VIEW_MANAGERS.NATIVE
  )
}

export default AdNativeComponent
