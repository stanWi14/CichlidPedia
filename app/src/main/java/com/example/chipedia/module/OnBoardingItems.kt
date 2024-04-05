package com.example.chipedia.module

import com.example.chipedia.R

class OnBoardingItems(
    val image: Int,
    val title: Int,
    val subtitle: Int,
    val desc: Int
) {
    companion object {
        fun getData(): List<OnBoardingItems> {
            return listOf(
                OnBoardingItems(
                    R.drawable.onboard1,
                    R.string.onBoardingTitle1,
                    R.string.onBoardingSubTitle1,
                    R.string.onBoardingText1
                ),
                OnBoardingItems(
                    R.drawable.onboard2,
                    R.string.onBoardingTitle2,
                    R.string.onBoardingSubTitle2,
                    R.string.onBoardingText1
                ),
                OnBoardingItems(
                    R.drawable.onboard4,
                    R.string.onBoardingTitle3,
                    R.string.onBoardingSubTitle3,
                    R.string.onBoardingText1
                )
            )
        }
    }
}