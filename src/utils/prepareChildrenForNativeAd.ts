// @ts-nocheck
import type { ReactNode } from 'react'
import * as React from 'react'

export const prepareChildrenForNativeAd = (
  children: ReactNode | ReactNode[],
  uniqSuffix: string
) => {
  React.Children.forEach(children, prepareChildForNativeAd(uniqSuffix))
  React.Children.toArray(children).forEach((view) => {
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
  return children
}
