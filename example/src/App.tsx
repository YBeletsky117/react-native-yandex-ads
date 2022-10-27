// @ts-nocheck
import * as React from 'react'

import { Dimensions, Platform, StyleSheet, View } from 'react-native'
import { AdNative, NativeAdTypes } from '@beletsky/react-native-yandex-ads'

const adsWidth = Dimensions.get('screen').width - 32
export default function App() {
  return (
    <View style={styles.container}>
      <View style={{ backgroundColor: 'rgba(1,1,1,.2)' }}>
        <AdNative.Container
          // adUnitId="R-M-338228-11"
          adUnitId="R-M-DEMO-native-i"
          config={{
            favicon: {
              width: 1,
              height: 1
            },
            age: {
              fontSize: 11,
              color: '#8b8d93'
            },
            title: {
              fontSize: 11,
              color: '#8b8d93'
            },
            icon: {
              width: 1,
              height: 1
            },
            feedback: {
              width: 10,
              height: 10
            },
            body: {
              fontSize: 16,
              color: '#d7dae0'
            },
            domain: {
              color: '#8b8d93',
              fontSize: 13
            },
            media: {
              width: 85,
              height: 85
            }
          }}
          height={Platform.select({ ios: 170, android: 117 })}
          key={'3'}
          style={{
            backgroundColor: Platform.select({
              ios: 'transparent',
              android: '#2b2d34'
            }),
            width: adsWidth,
            height: Platform.select({ ios: 170, android: 117 }),
            flexDirection: 'row',
            paddingVertical: 10,
            ...Platform.select({
              android: {
                paddingLeft: 10,
                paddingRight: 7
              }
            })
          }}
          width={adsWidth}
        >
          <View>
            <View
              style={{
                display: 'flex',
                flexDirection: 'row',
                overflow: 'visible',
                alignItems: 'center'
              }}
            >
              {Platform.select({
                android: (
                  <View style={{ top: -3 }}>
                    <View
                      style={{
                        width: 13,
                        height: 13,
                        marginRight: 6,
                        alignItems: 'center',
                        justifyContent: 'center'
                      }}
                    >
                      <View
                        style={{
                          width: 13,
                          height: 13
                        }}
                      />
                    </View>
                  </View>
                )
              })}
              <AdNative.Image
                style={{ display: 'none' }}
                uniqYandexId={NativeAdTypes.favicon}
              />
              <AdNative.Text
                style={{ height: 16 }}
                uniqYandexId={NativeAdTypes.age}
              />
              <AdNative.Text
                lineBreakMode="tail"
                numberOfLines={1}
                style={{
                  height: 16,
                  width: Dimensions.get('screen').width - 157,
                  fontSize: 11,
                  color: '#8b8d93',
                  fontFamily: 'Roboto-Regular',
                  lineHeight: 16,
                  marginBottom: 2
                }}
                uniqYandexId={NativeAdTypes.title}
              />
            </View>
            <AdNative.Text
              lineBreakMode="tail"
              numberOfLines={3}
              style={{
                height: 66,
                width: Dimensions.get('screen').width - 157,
                minHeight: 30,
                fontSize: 16,
                fontFamily: 'Roboto-Regular',
                lineHeight: 22,
                letterSpacing: 0.25,
                fontWeight: '500'
              }}
              uniqYandexId={NativeAdTypes.body}
            />
            <AdNative.Text
              style={{
                height: 16,
                fontSize: 12.5,
                color: '#8b8d93',
                fontStyle: 'italic',
                fontWeight: 'normal',
                letterSpacing: 0.192
              }}
              uniqYandexId={NativeAdTypes.domain}
            />
            <AdNative.Image
              style={{
                width: 1,
                hegiht: 1
              }}
              uniqYandexId={NativeAdTypes.icon}
            />

            <AdNative.Image
              style={{
                width: 10,
                hegiht: 10
              }}
              uniqYandexId={NativeAdTypes.feedback}
            />

            <AdNative.Text
              style={{ height: 13 }}
              uniqYandexId={NativeAdTypes.warning}
            />
            <AdNative.Text
              style={{ height: 13 }}
              uniqYandexId={NativeAdTypes.sponsored}
            />
          </View>

          <AdNative.Media
            height={85}
            style={{
              borderRadius: 5,
              overflow: 'hidden'
            }}
            uniqYandexId={NativeAdTypes.media}
            width={85}
          />
        </AdNative.Container>
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'grey'
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20
  }
})
