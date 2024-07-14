package me.luki.website.utils

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.silk.theme.colors.ColorMode

class SitePalette(
    val accent: Color = CustomColors.Purple,
    val nearBackground: Color
)

private object SitePalettes {
    val dark = SitePalette(nearBackground = CustomColors.NearDark)
    val light = SitePalette(nearBackground = CustomColors.NearLight)
}

fun ColorMode.toSitePalette(): SitePalette {
    return when (this) {
        ColorMode.DARK -> SitePalettes.dark
        ColorMode.LIGHT -> SitePalettes.light
    }
}
