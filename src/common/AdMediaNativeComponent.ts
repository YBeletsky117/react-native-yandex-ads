import { HostComponent, requireNativeComponent, ViewProps } from 'react-native'
import { cacheNativeView, getCachedNativeView } from '../utils'
import RES from '../resources'

interface AdMediaComponentType extends ViewProps {
  width: number
  height: number
}

let AdMediaNativeComponent: HostComponent<AdMediaComponentType>
const globalView = getCachedNativeView<typeof AdMediaNativeComponent>()

if (__DEV__ && !globalView) {
  AdMediaNativeComponent = requireNativeComponent<AdMediaComponentType>(
    RES.VIEW_MANAGERS.MEDIA
  )
  cacheNativeView<typeof AdMediaNativeComponent>(AdMediaNativeComponent)
} else if (__DEV__ && getCachedNativeView<typeof AdMediaNativeComponent>()) {
  AdMediaNativeComponent = getCachedNativeView<typeof AdMediaNativeComponent>()
} else {
  AdMediaNativeComponent = requireNativeComponent<AdMediaComponentType>(
    RES.VIEW_MANAGERS.MEDIA
  )
}

export default AdMediaNativeComponent
