// @ts-nocheck
import React, { FC } from 'react'
import { Platform, requireNativeComponent } from 'react-native'
import type { AdBannerType } from '../resources'
// import { AdBannerNativeComponent } from '../common'
import type {
  NativeEventType,
  NativeFailedEventType,
  NativeImpressionEventType
} from '../common/AdBannerNativeComponent'
import RES from '../resources'

const RNYAMBanner = requireNativeComponent(RES.VIEW_MANAGERS.BANNER)
type Props = {
  adUnitId: string
  size?: AdBannerType
  /** only IOS */
  place?: 'top' | 'botttom'
  customSize?: {
    width: number
    height: number
  }
  requestParams?: Record<string, string>
  onDidLoad?: (adUnitID?: string | null) => void
  onClick?: (adUnitID: string | null) => void
  onDidTrackImpression?: (adUnitID: string | null, data?: string) => void
  onDidFailLoading?: (adUnitID: string | null, error?: string) => void
  onWillLeaveApp?: (adUnitID: string | null) => void
  onWillPresent?: (adUnitID: string | null) => void
  onDidDismiss?: (adUnitID: string | null) => void
  onDidReturnedToApplication?: (adUnitID: string | null) => void
}

const Banner: FC<Props> = ({
  adUnitId,
  place = 'top',
  size,
  requestParams,
  customSize,
  onDidLoad,
  onClick,
  onDidTrackImpression,
  onDidFailLoading,
  onWillLeaveApp,
  onWillPresent,
  onDidDismiss
}) => {
  const onDidLoadHandler = (event: NativeEventType) => {
    onDidLoad?.(event.nativeEvent.adUnitID)
  }
  const onClickHandler = (event: NativeEventType) => {
    onClick?.(event.nativeEvent.adUnitID)
  }
  const onDidTrackImpressionHandler = (event: NativeImpressionEventType) => {
    onDidTrackImpression?.(event.nativeEvent.adUnitID, event.nativeEvent.data)
  }
  const onDidFailLoadingHandler = (event: NativeFailedEventType) => {
    onDidFailLoading?.(
      event.nativeEvent.adUnitID,
      event.nativeEvent.errorMessage
    )
  }
  const onWillLeaveAppHandler = (event: NativeEventType) => {
    onWillLeaveApp?.(event.nativeEvent.adUnitID)
  }
  const onWillPresentHandler = (event: NativeEventType) => {
    onWillPresent?.(event.nativeEvent.adUnitID)
  }
  const onDidDismissHandler = (event: NativeEventType) => {
    onDidDismiss?.(event.nativeEvent.adUnitID)
  }
  const onDidReturnedToApplicationHandler = (event: NativeEventType) => {
    onDidDismiss?.(event.nativeEvent.adUnitID)
  }

  return (
    <RNYAMBanner
      {...{
        ...(requestParams
          ? {
              requestParams
            }
          : {}),
        adUnitID: adUnitId,
        ...(customSize
          ? {
              customSize: [customSize.width, customSize.height]
            }
          : {
              size
            }),
        style: {
          backgroundColor: 'transparent',
          width: customSize ? customSize.width : RES.bannerWidth[size],
          height: customSize ? customSize.height : RES.bannerHeight[size]
        },
        onDidLoad: onDidLoadHandler,
        onClick: onClickHandler,
        onDidTrackImpression: onDidTrackImpressionHandler,
        onDidFailLoading: onDidFailLoadingHandler,
        onWillLeaveApp: onWillLeaveAppHandler
      }}
      {...Platform.select({
        android: {
          onDidReturnedToApplication: onDidReturnedToApplicationHandler
        },
        ios: {
          place,
          onWillPresent: onWillPresentHandler,
          onDidDismiss: onDidDismissHandler
        }
      })}
    />
  )
}

export default Banner
