import { HostComponent, requireNativeComponent, ViewProps } from 'react-native'
import { cacheNativeView, getCachedNativeView } from '../utils'
import RES from '../resources'

let AdImageNativeComponent: HostComponent<ViewProps>
const globalView = getCachedNativeView<typeof AdImageNativeComponent>()

if (__DEV__ && !globalView) {
  AdImageNativeComponent = requireNativeComponent<ViewProps>(
    RES.VIEW_MANAGERS.IMAGE!
  )
  cacheNativeView<typeof AdImageNativeComponent>(AdImageNativeComponent)
} else if (__DEV__ && getCachedNativeView<typeof AdImageNativeComponent>()) {
  AdImageNativeComponent = getCachedNativeView<typeof AdImageNativeComponent>()
} else {
  AdImageNativeComponent = requireNativeComponent<ViewProps>(
    RES.VIEW_MANAGERS.IMAGE!
  )
}

export default AdImageNativeComponent
