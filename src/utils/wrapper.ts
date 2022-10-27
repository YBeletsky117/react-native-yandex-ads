export const cacheNativeView = <T>(value: T) => {
  /* @ts-ignore */
  global.NativeComponent = value
}

export const getCachedNativeView = <T>(): T => {
  /* @ts-ignore */
  return global.NativeComponent
}
