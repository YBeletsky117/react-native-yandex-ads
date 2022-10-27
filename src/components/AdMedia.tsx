// @ts-nocheck
import React, { FC } from 'react'
import { View, ViewProps } from 'react-native'
import AdMediaNativeComponent from '../common/AdMediaNativeComponent'
import { DEFAULT_CONFIG as defConfig } from '../resources'

export interface AdMediaProps {
  width?: number
  height?: number
  style?: ViewProps['style']
  uniqYandexId: string
}

export const AdMedia: FC<AdMediaProps> = ({
  nativeID,
  width = defConfig.media.width,
  height = defConfig.media.height,
  style
}) => {
  return (
    <View
      style={[
        {
          backgroundColor: 'transparent'
        },
        style,
        {
          width,
          height
        }
      ]}
    >
      {nativeID ? (
        <AdMediaNativeComponent {...{ width, height, nativeID }} />
      ) : null}
    </View>
  )
}
