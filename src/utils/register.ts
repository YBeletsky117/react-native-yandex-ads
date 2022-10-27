import { Platform, requireNativeComponent, UIManager } from 'react-native'

const LINKING_ERROR =
  `The package 'react-native-yandex-ads' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n'

export const registerComponent = <ComponentPropType>(componentName: string) =>
  UIManager.getViewManagerConfig(componentName) != null
    ? requireNativeComponent<ComponentPropType>(componentName)
    : () => {
        throw new Error(LINKING_ERROR)
      }
