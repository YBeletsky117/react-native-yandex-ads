// @ts-nocheck
import type { ReactNode } from 'react'
import * as React from 'react'
import { prepareChildForNativeAd } from './prepareChildForNativeAd'

export const prepareChildrenForNativeAd = (
  children: ReactNode | ReactNode[],
  uniqSuffix: string
) => {
  const _c = children
  if (_c?.length) {
    React.Children.forEach(_c, prepareChildForNativeAd(uniqSuffix))
    React.Children.toArray(_c).forEach((view) => {
      React.Children.toArray(view.props.children).forEach((view2) =>
        React.Children.forEach(
          view2.props.children,
          prepareChildForNativeAd(uniqSuffix)
        )
      )
      React.Children.forEach(
        view.props.children,
        prepareChildForNativeAd(uniqSuffix)
      )
    })
  }
  return _c
}
