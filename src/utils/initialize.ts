import { NativeModules, Platform } from 'react-native'
import RES from '../resources'

export const initialize = (
  isDebug = false,
  userLocation = false,
  userConsent = false
) => {
  if (Platform.OS === 'android') {
    if (NativeModules?.[RES.MODULES.MAIN]) {
      return NativeModules[RES.MODULES.MAIN].initialize(
        isDebug,
        userLocation,
        userConsent
      )
    }
  }
}
