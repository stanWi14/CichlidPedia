package com.example.chipedia.module

import com.example.chipedia.R


data class SubMenuItem(
    val title: Int,
    val image: Int,
    val value: Int
)

class MenuItems(
    val image: Int,
    val title: Int,
    val value: Int,
    val subItems: List<SubMenuItem> = listOf()
) {
    companion object {
        fun getData(): List<MenuItems> {
            return listOf(
                MenuItems(
                    R.drawable.africanmenu,

                    R.string.menuTitleAfrica,
                    R.string.menuTitleAfrica,
                    listOf(
                        SubMenuItem(
                            R.string.menuTitleMalawi,
                            R.drawable.malawimenu,
                            R.string.valueMalawi
                        ),
                        SubMenuItem(
                            R.string.menuTitleTanganyika,
                            R.drawable.tanganyikanmenu,
                            R.string.valueTanganyika
                        ),
                        SubMenuItem(
                            R.string.menuTitleVictoria,
                            R.drawable.victoriamenu,
                            R.string.valueVictoria
                        ),
                        SubMenuItem(
                            R.string.menuTitleCongo,
                            R.drawable.congomenu,
                            R.string.valueCongo
                        ),
                        SubMenuItem(
                            R.string.menuTitleMadagascar,
                            R.drawable.madagascarmenu,
                            R.string.valueMadagascar
                        )
                    )
                ),
                MenuItems(
                    R.drawable.americanmenu,
                    R.string.menuTitleAmerica,
                    R.string.menuTitleAmerica,
                    listOf(
                        SubMenuItem(
                            R.string.menuTitleSouthAmerica,
                            R.drawable.southamerianmenu,
                            R.string.valueSouthAmerica
                        ),
                        SubMenuItem(
                            R.string.menuTitleCentralAmerica,
                            R.drawable.centralamericamenu,
                            R.string.valueCentralAmerica
                        )
                    )

                ),
                MenuItems(
                    R.drawable.asianmenu,
                    R.string.menuTitleAsia,
                    R.string.menuTitleAsia,
                    listOf(
                        SubMenuItem(
                            R.string.menuTitleIndia,
                            R.drawable.indiamenu,
                            R.string.valueIndia
                        )
                    )
                )
            )
        }
    }
}