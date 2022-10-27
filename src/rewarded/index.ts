import { NativeEventEmitter, NativeModules } from 'react-native'
import RES from '../resources'

const module = NativeModules[RES.MODULES.REWARDED]
const eventEmitter = new NativeEventEmitter(module)

const Rewarded = {
  show: async (AdUnitID: string): Promise<boolean> => {
    try {
      return await module.showAd(AdUnitID)
    } catch (e) {
      console.error(e)
      throw e
    }
  },
  addEventListener: (
    eventType: keyof typeof RES.RewardedEventTypes,
    callback: (...args: any[]) => void,
    context?: any
  ) => {
    const listener = eventEmitter.addListener(
      RES.RewardedEventTypes[eventType],
      callback,
      context
    )
    return {
      remove: listener.remove
    }
  }
}

export default Rewarded
