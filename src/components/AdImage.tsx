import React from 'react'
import AdImageNativeComponent from '../common/AdImageNativeComponent'
import { DEFAULT_CONFIG } from '../resources'

// @ts-ignore
export const AdImage = (props) => (
  <AdImageNativeComponent
    {...props}
    style={[DEFAULT_CONFIG.icon, props?.style]}
  />
)
