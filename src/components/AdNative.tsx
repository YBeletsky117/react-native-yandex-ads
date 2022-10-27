import React, { forwardRef } from 'react'
import AdNativeComponent, { AdNativeProps } from '../common/AdNativeComponent'

export const AdNative = forwardRef<any, AdNativeProps>((props, ref) => {
  return <AdNativeComponent ref={ref} {...props} />
})
