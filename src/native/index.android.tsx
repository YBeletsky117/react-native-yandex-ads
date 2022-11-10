// @ts-nocheck
import React, { FC, useRef, useState } from 'react'
import {
  findNodeHandle,
  PixelRatio,
  StyleProp,
  StyleSheet,
  UIManager,
  View,
  ViewProps
} from 'react-native'
import { AdNative as AdNativeComponent } from '../components'
import RES, {
  DEFAULT_CONFIG as defConfig,
  YA_NATIVE_COMPONENTS as componentsName,
  YA_NATIVE_ID
} from '../resources'
import { ErrorException } from '../utils'

const createFragment = (viewId: number) => {
  const command = UIManager.getViewManagerConfig(RES.VIEW_MANAGERS.NATIVE)
    .Commands.create
  if (command) {
    UIManager.dispatchViewManagerCommand(viewId, command.toString(), [
      viewId,
      YA_NATIVE_ID.title,
      YA_NATIVE_ID.body,
      YA_NATIVE_ID.warning,
      YA_NATIVE_ID.sponsored,
      YA_NATIVE_ID.age,
      YA_NATIVE_ID.domain,
      YA_NATIVE_ID.icon,
      YA_NATIVE_ID.favicon,
      YA_NATIVE_ID.feedback,
      YA_NATIVE_ID.media
    ])
  }
}

export const AdNative: FC<{
  adUnitId: string
  style?: StyleProp<ViewProps>
  width?: number
  height?: number
  config?: typeof defConfig
}> = ({ adUnitId, style, config, width = 100, height = 100, ...props }) => {
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

  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [isLoading, setLoading] = useState(true)

  if (!React.Children.count(props.children)) {
    throw ErrorException('A native ad block must contain children inside it')
  }

  return (
    <AdNativeComponent
      adUnitId={adUnitId}
      style={{
        flexDirection: 'row',
        display: 'flex',
        width,
        height
      }}
      height={PixelRatio.getPixelSizeForLayoutSize(height)}
      width={PixelRatio.getPixelSizeForLayoutSize(width)}
      titleColor={config?.title?.color || defConfig.title.color}
      titleFontSize={config?.title?.fontSize || defConfig.title.fontSize}
      bodyColor={config?.body?.color || defConfig.body.color}
      bodyFontSize={config?.body?.fontSize || defConfig.body.fontSize}
      warningColor={config?.warning?.color || defConfig.warning.color}
      warningFontSize={config?.warning?.fontSize || defConfig.warning.fontSize}
      sponsoredColor={config?.sponsored?.color || defConfig.sponsored.color}
      sponsoredFontSize={
        config?.sponsored?.fontSize || defConfig.sponsored.fontSize
      }
      ageColor={config?.age?.color || defConfig.age.color}
      ageFontSize={config?.age?.fontSize || defConfig.age.fontSize}
      domainColor={config?.domain?.color || defConfig.domain.color}
      domainFontSize={config?.domain?.fontSize || defConfig.domain.fontSize}
      mediaWidth={config?.media?.width || defConfig.media.width}
      mediaHeight={config?.media?.height || defConfig.media.height}
      titleLines={config?.title?.numberOfLines || defConfig.title.numberOfLines}
      bodyLines={config?.body?.numberOfLines || defConfig.body.numberOfLines}
      warningLines={
        config?.warning?.numberOfLines || defConfig.warning.numberOfLines
      }
      sponsoredLines={
        config?.sponsored?.numberOfLines || defConfig.sponsored.numberOfLines
      }
      ageLines={config?.age?.numberOfLines || defConfig.age.numberOfLines}
      domainLines={
        config?.domain?.numberOfLines || defConfig.domain.numberOfLines
      }
      onLoadEnd={() => setLoading(false)}
      ref={ref}
    >
      <View
        {...props}
        nativeID={componentsName.container}
        onLayout={() => {
          if (React.Children.count(props.children)) {
            if (ref && 'current' in ref) {
              const viewId = findNodeHandle(ref.current)

              if (viewId) {
                setLoading(true)
                createFragment(viewId)
              }
            }
          }
        }}
        style={[style, defStyle.content]}
      >
        {props.children}
      </View>
    </AdNativeComponent>
  )
}

const defStyle = StyleSheet.create({
  content: {
    flex: 1
  }
})
