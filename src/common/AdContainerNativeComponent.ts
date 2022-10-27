import { HostComponent, requireNativeComponent, ViewStyle } from 'react-native'
import { cacheNativeView, getCachedNativeView } from '../utils'
import RES from '../resources'

type AdContainerComponentType = {
  width: number
  height: number
  style: ViewStyle
  adUnitID: string
  manualImpressionsEnabled: boolean
  onNativeEvent: (event: { nativeEvent: any }) => void
}

let AdContainerNativeComponent: HostComponent<AdContainerComponentType>
const globalView = getCachedNativeView<typeof AdContainerNativeComponent>()

if (__DEV__ && !globalView) {
  AdContainerNativeComponent = requireNativeComponent<AdContainerComponentType>(
    RES.VIEW_MANAGERS.CONTAINER
  )
  cacheNativeView<typeof AdContainerNativeComponent>(AdContainerNativeComponent)
} else if (
  __DEV__ &&
  getCachedNativeView<typeof AdContainerNativeComponent>()
) {
  AdContainerNativeComponent =
    getCachedNativeView<typeof AdContainerNativeComponent>()
} else {
  AdContainerNativeComponent = requireNativeComponent<AdContainerComponentType>(
    RES.VIEW_MANAGERS.CONTAINER
  )
}

export default AdContainerNativeComponent
