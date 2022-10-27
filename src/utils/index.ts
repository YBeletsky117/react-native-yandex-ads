import { Platform } from 'react-native'

export const isAndroid = Platform.OS === 'android'
export const isIOS = Platform.OS === 'ios'
export const ErrorException = (text: string) => {
  throw Error(`Yandex Modile Ads returned an error\nError: ${text}`)
}

export * from './register'
export * from './initialize'
export * from './prepareChildForNativeAd'
export * from './prepareChildrenForNativeAd'
export * from './wrapper'
