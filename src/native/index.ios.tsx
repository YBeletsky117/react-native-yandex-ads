// @ts-nocheck
import React, { FC, useRef } from 'react'
import type { StyleProp, View, ViewProps } from 'react-native'
import { AdNative as AdNativeComponent } from '../components'

export const AdNative: FC<{
  adUnitId: string
  style?: StyleProp<ViewProps>
  width?: number
  heiheightght?: number
}> = ({ adUnitId, style, width = 100, height = 100 }) => {
  if (!adUnitId) {
    throw Error(
      '[YandexAdsNative]\nThe adUnitId parameter is not set. It is mandatory. Perhaps you forgot to specify the adUnitId of the block?'
    )
  }
  if (!style?.width) {
    throw Error(
      '[YandexAdsNative]\nThe ad block width parameter is not set. It is mandatory. Perhaps you forgot to specify the width of the block?'
    )
  }
  if (!style?.height) {
    throw Error(
      '[YandexAdsNative]\nThe ad block height parameter is not set. It is mandatory. Perhaps you forgot to specify the height of the block?'
    )
  }
  const ref = useRef<View>(null)

  return (
    <AdNativeComponent
      adUnitId={adUnitId}
      style={{
        flexDirection: 'row',
        display: 'flex',
        width,
        height
      }}
      height={height}
      width={width}
      onDidLoad={() => console.log('END load')}
      onWillLoad={() => console.log('START load')}
      onDidTrackImpression={() => console.log('onDidTrackImpression')}
      onWillPresent={() => console.log('onWillPresent')}
      onWillLeaveApp={() => console.log('onWillLeaveApp')}
      onDidDismiss={() => console.log('onDidDismiss')}
      onClick={() => console.log('onClick')}
      onClose={() => console.log('onClose')}
      onTouchEnd={() => console.log('kajsdjka')}
      ref={ref}
    />
  )
}
