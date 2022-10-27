// @ts-nocheck
import React, { forwardRef } from 'react'
import AdContainerNativeComponent from '../common/AdContainerNativeComponent'

export const AdContainer = forwardRef((props, ref) => {
  return <AdContainerNativeComponent ref={ref} {...props} />
})
