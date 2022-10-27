// @ts-nocheck
import type { View, ViewProps } from 'react-native'
import { YA_NATIVE_COMPONENTS, YA_NATIVE_ID } from '../resources'

export const prepareChildForNativeAd = (suffix: string) => {
  return (child: View & { props?: ViewProps }) => {
    if (child) {
      const snapShot = child
      const PROP_KEY = 'uniqYandexId'
      const props = snapShot?.props
      if (PROP_KEY in props) {
        if (Object.values(YA_NATIVE_COMPONENTS).includes(props[PROP_KEY])) {
          const YaNativeIdEntries = Object.entries(YA_NATIVE_COMPONENTS)
          const foundIndexOfNativeIds = Object.values(
            YA_NATIVE_COMPONENTS
          ).findIndex((el: string) => el === props[PROP_KEY])
          if (YaNativeIdEntries && foundIndexOfNativeIds) {
            const foundEntries = YaNativeIdEntries[foundIndexOfNativeIds]
            if (foundEntries) {
              const key = YaNativeIdEntries[foundIndexOfNativeIds][0]
              if (key) {
                const nativeID = YA_NATIVE_ID[key] + suffix
                if (snapShot?.props) {
                  snapShot.props = {
                    ...snapShot.props,
                    nativeID
                  }
                  return snapShot
                }
              }
            }
          }
        }
      }
    }
    return child
  }
}
