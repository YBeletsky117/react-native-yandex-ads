import { NativeModules, Platform } from 'react-native'

export const initialize = (
  isDebug = false,
  userLocation = false,
  userConsent = false
) =>
  Platform.select({
    ios: () => {},
    android: NativeModules.RNYAMModule.initialize(
      isDebug,
      userLocation,
      userConsent
    )
  })
