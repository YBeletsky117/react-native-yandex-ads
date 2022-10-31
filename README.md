# Yandex Ads for React Native (beta)

This library was created to display Yandex ads in mobile applications developed using the React Native (without fabric)
framework

## Installation

### for npm

```sh
npm install @beletsky/react-native-yandex-ads@beta
```

### for yarn

```sh
yarn add @beletsky/react-native-yandex-ads@beta
```

## Usage

# Native

<p style='color: #ffffff; background-color: rgb(183,47,47)'>Attention, in this version, the display of ads in Android and iOS is different, we are making every effort to create a single visual part</p>

## `AdNative.Container`

| Property                  | Type                                    | Required          | Default     |
|---------------------------|:---------------------------------------:|------------------:|------------:|
| adUnitId                  | string                                  |✅                 | none        |
| width                     | number                                  |✅                 | none        |
| height                    | number                                  |✅                 | none        |
| config (only Android)     | typeof defConfig (see deep)             |✅                 | none        |

## `AdNative.Image` (only Android)

| Property                  | Type                                    | Required          | Default     |
|---------------------------|:---------------------------------------:|------------------:|------------:|
| uniqYandexId              | NativeAdTypes                           |✅                 | none        |

## `AdNative.Media` (only Android)

| Property                  | Type                                    | Required          | Default     |
|---------------------------|:---------------------------------------:|------------------:|------------:|
| uniqYandexId              | NativeAdTypes                           |✅                 | none        |

## `AdNative.Text` (only Android)

| Property                  | Type                                    | Required          | Default     |
|---------------------------|:---------------------------------------:|------------------:|------------:|
| uniqYandexId              | NativeAdTypes                           |✅                 | none        |

## for Android

```jsx
import React from 'react'
import { AdNative, NativeAdTypes } from '@beletsky/react-native-yandex-ads';
import { StyleSheet } from 'react-native';

const ADS_NATIVE_SIZE = {
  width: 400,
  height: 117
}

const ADS_NATIVE_CONFIG = {
  favicon: {
    width: 1,
    height: 1,
  },
  age: {
    fontSize: 11,
    color: '#444',
  },
  title: {
    fontSize: 11,
    color: '#444',
  },
  icon: {
    width: 1,
    height: 1,
  },
  feedback: {
    width: 10,
    height: 10,
  },
  body: {
    fontSize: 16,
    color: '#444',
  },
  domain: {
    color: '#444',
    fontSize: 13,
  },
  media: {
    width: 85,
    height: 85,
  },
}

export function App() {
  return (
    <AdNative.Container
      adUnitId={"R-M-ADS_ID"}
      config={ADS_NATIVE_CONFIG}
      height={ADS_NATIVE_SIZE.height}
      style={styles.container}
      width={ADS_NATIVE_SIZE.width}
    >
      <View>
        <View
          style={styles.contentWrapper}>
          <AdNative.Image
            style={styles.image}
            uniqYandexId={NativeAdTypes.favicon}
          />
          <AdNative.Text
            style={styles.age}
            uniqYandexId={NativeAdTypes.age}
          />
          <AdNative.Text
            lineBreakMode="tail"
            numberOfLines={1}
            style={styles.title}
            uniqYandexId={NativeAdTypes.title}
          />
        </View>
        <AdNative.Text
          lineBreakMode="tail"
          numberOfLines={3}
          style={styles.body}
          uniqYandexId={NativeAdTypes.body}
        />
        <AdNative.Text
          style={styles.domain}
          uniqYandexId={NativeAdTypes.domain}
        />
        <AdNative.Image
          style={styles.icon}
          uniqYandexId={NativeAdTypes.icon}
        />
        <AdNative.Image
          style={styles.feedback}
          uniqYandexId={NativeAdTypes.feedback}
        />
        <AdNative.Text
          style={styles.warning}
          uniqYandexId={NativeAdTypes.warning}
        />
        <AdNative.Text
          style={styles.sponsored}
          uniqYandexId={NativeAdTypes.sponsored}
        />
      </View>
      <AdNative.Media
        height={85}
        style={styles.media}
        uniqYandexId={NativeAdTypes.media}
        width={85}
      />

    </AdNative.Container>
  )
}

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#000',
    width: ADS_NATIVE_SIZE.width,
    height: ADS_NATIVE_SIZE.height,
    flexDirection: 'row',
    paddingVertical: 10,
    paddingLeft: 10,
    paddingRight: 7,
  },
  contentWrapper: {
    display: 'flex',
    flexDirection: 'row',
    overflow: 'visible',
    alignItems: 'center',
  },
  image: {
    display: 'none'
  },
  age: {
    height: 16
  },
  title: {
    height: 16,
    width: ADS_NATIVE_SIZE.width - 157,
    fontSize: 11,
    color: '#444',
    fontFamily: 'Roboto-Regular',
    lineHeight: 16,
    marginBottom: 2,
  },
  body: {
    height: 66,
    width: ADS_NATIVE_SIZE.width - 157,
    minHeight: 30,
    fontSize: 16,
    fontFamily: 'Roboto-Regular',
    lineHeight: 22,
    letterSpacing: 0.25,
    fontWeight: '500',
  },
  domain: {
    height: 16,
    fontSize: 12.5,
    color: '#444',
    fontStyle: 'italic',
    fontWeight: 'normal',
    letterSpacing: 0.192,
  },
  icon: {
    width: 1,
    height: 1,
  },
  feedback: {
    width: 10,
    height: 10,
  },
  warning: {
    height: 13
  },
  sponsored: {
    height: 13
  },
  media: {
    borderRadius: 5,
    overflow: 'hidden',
  }
})

```

## for IOS

```jsx
import React from 'react'
import { AdNative } from '@beletsky/react-native-yandex-ads';
import { StyleSheet } from 'react-native';

const ADS_NATIVE_SIZE = {
  width: 400,
  height: 117
}

export function App() {
  return (
    <AdNative.Container
      adUnitId={"R-M-ADS_ID"}
      height={ADS_NATIVE_SIZE.height}
      style={styles.container}
      width={ADS_NATIVE_SIZE.width}
    />
  )
}

const styles = StyleSheet.create({
  container: {
    width: ADS_NATIVE_SIZE.width,
    height: ADS_NATIVE_SIZE.height,
    flexDirection: 'row',
    paddingVertical: 10,
    paddingLeft: 10,
    paddingRight: 7,
  }
})

```

# Banner

| Property                  | Type                                    | Required          | Default     |
|---------------------------|:---------------------------------------:|------------------:|------------:|
| adUnitId                  | string                                  |✅                 | none        |
| place (only IOS)          | 'top' or 'bottom'                       |🚫                 | 'top'       |
| size                      | AdBannerType                            |✅ (or customSize) | none        |
| customSize                | { width, height }                       |✅ (or size)       | none        |
| onDidLoad                 | (adId?: string) => void                 |🚫                 | none        |
| onClick                   | (adId?: string) => void                 |🚫                 | none        |
| onWillLeaveApp            | (adId?: string) => void                 |🚫                 | none        |
| onWillPresent             | (adId?: string) => void                 |🚫                 | none        |
| onDidDismiss              | (adId?: string) => void                 |🚫                 | none        |
| onDidReturnedToApplication| (adId?: string) => void                 |🚫                 | none        |
| onDidTrackImpression      | (adId?: string, data?: string) => void  |🚫                 | none        |
| onDidFailLoading          | (adId?: string, error?: string) => void |🚫                 | none        |

```jsx
import React from 'react'
import { AdBanner } from "@beletsky/react-native-yandex-ads";

// ...

return (
  <AdBanner
    onDidLoad={() => console.log('on did load')}
    onDidFailLoading={() => console.log('failed load')}
    adUnitId={"R-M-ADS_ID"}
    size={AdBannerType.BANNER_300x300}
  />
)
```

# Banner

| Property                  | Type                                    | Required          | Default     |
|---------------------------|:---------------------------------------:|------------------:|------------:|
| adUnitId                  | string                                  |✅                 | none        |
| place (only IOS)          | 'top' or 'bottom'                       |🚫                 | 'top'       |
| size                      | AdBannerType                            |✅ (or customSize) | none        |
| customSize                | { width, height }                       |✅ (or size)       | none        |
| onDidLoad                 | (adId?: string) => void                 |🚫                 | none        |
| onClick                   | (adId?: string) => void                 |🚫                 | none        |
| onWillLeaveApp            | (adId?: string) => void                 |🚫                 | none        |
| onWillPresent             | (adId?: string) => void                 |🚫                 | none        |
| onDidDismiss              | (adId?: string) => void                 |🚫                 | none        |
| onDidReturnedToApplication| (adId?: string) => void                 |🚫                 | none        |
| onDidTrackImpression      | (adId?: string, data?: string) => void  |🚫                 | none        |
| onDidFailLoading          | (adId?: string, error?: string) => void |🚫                 | none        |

```jsx
import React from 'react'
import { AdBanner } from "@beletsky/react-native-yandex-ads";

// ...

return (
  <AdBanner
    onDidLoad={() => console.log('on did load')}
    onDidFailLoading={() => console.log('failed load')}
    adUnitId={"R-M-ADS_ID"}
    size={AdBannerType.BANNER_300x300}
  />
)
```

# Interstitial

| Method                    | Type                                           | Required             | Default     |
|---------------------------|:----------------------------------------------:|---------------------:|------------:|
| show()                    | (adUnitId: string) => boolean                  | adUnitId is required | none        |
| addEventListener()        | (eventType, callback) => { remove: () => void }|

```jsx
import { InterstitialEventTypes as EventTypes, AdInterstitial } from "@beletsky/react-native-yandex-ads";
import React, { useEffect } from 'react';

// ...

function App() {
  useEffect(() => {
    const adsListener = AdInterstitial.show(EventTypes.didLoad,
      (source) => {
        console.log(source)
      })
    return () => {
      adsListener.remove()
    }
  }, [])
  return // ...
}
```

# Rewarded

| Method                    | Type                                           | Required             | Default     |
|---------------------------|:----------------------------------------------:|---------------------:|------------:|
| show()                    | (adUnitId: string) => boolean                  | adUnitId is required | none        |
| addEventListener()        | (eventType, callback) => { remove: () => void }|

```jsx
import { RewardedEventTypes as EventTypes, AdRewarded } from "@beletsky/react-native-yandex-ads";
import React, { useEffect } from 'react';

// ...

function App() {
  useEffect(() => {
    const adsListener = AdRewarded.show(EventTypes.didLoad,
      (source) => {
        console.log(source)
      })
    return () => {
      adsListener.remove()
    }
  }, [])
  return // ...
}
```

## License

MIT

---

### Created by Yaroslav Beletsky [@beletsky](https://github.com/YBeletsky117)
