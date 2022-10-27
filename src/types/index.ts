export type NativeEventType = {
  nativeEvent: {
    adUnitID: string | null
  }
}

export type NativeLoadEventType = {
  nativeEvent: {
    adUnitID?: string | null
    name?: string | null
    value?: string | null
    ordinal?: string | null
    age?: string | null
    body?: string | null
    callToAction?: string | null
    domain?: string | null
    price?: string | null
    reviewCount?: string | null
    sponsored?: string | null
    title?: string | null
    warning?: string | null
    imageSource?: string | null
    imageWidth?: string | null
    imageHeight?: string | null
    faviconSource?: string | null
    faviconWidth?: string | null
    faviconHeight?: string | null
    iconSource?: string | null
    iconWidth?: string | null
    iconHeight?: string | null
    isFeedbackAvailable?: string | null
    rating?: string | null
  }
}

export type NativeFailedEventType = {
  nativeEvent: {
    adUnitID: string | null
    error?: string
  }
}

export type NativeImpressionEventType = {
  nativeEvent: {
    adUnitID: string | null
    impressionData?: string
  }
}

export type NativeComponentProps = {
  adUnitID: string
  width: number
  height: number
  onDidLoad: (props: NativeEventType) => void
  onClick: (props: NativeEventType) => void
  onDidTrackImpression: (props: NativeImpressionEventType) => void
  onDidFailLoading: (props: NativeFailedEventType) => void
  onWillLeaveApp: (props: NativeEventType) => void
  onWillPresent: (props: NativeEventType) => void
  onDidDismiss: (props: NativeEventType) => void
  onClose: (props: NativeEventType) => void
}
