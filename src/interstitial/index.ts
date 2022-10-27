import { NativeEventEmitter, NativeModules } from 'react-native'
import RES from '../resources'

const module = NativeModules[RES.MODULES.INTERSTITIAL]
const eventEmitter = new NativeEventEmitter(module)

const Interstitial = {
  show: async (AdUnitID: string): Promise<boolean> => {
    try {
      return await module.showAd(AdUnitID)
    } catch (e) {
      console.error(e)
      throw e
    }
  },
  addEventListener: (
    eventType: keyof typeof RES.InterstitialEventTypes,
    callback: (prop: { adUnitID: string }) => void,
    context?: any
  ) => {
    const listener = eventEmitter.addListener(
      RES.InterstitialEventTypes[eventType],
      callback,
      context
    )
    return {
      remove: listener.remove
    }
  }
}

export default Interstitial
