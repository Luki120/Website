package me.luki.website.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.loadFromLocalStorage
import com.varabyte.kobweb.silk.theme.colors.palette.button
import com.varabyte.kobweb.silk.theme.colors.systemPreference
import com.varabyte.kobweb.silk.theme.modifyStyle
import kotlinx.browser.window
import org.jetbrains.compose.web.css.CSSMediaQuery
import org.jetbrains.compose.web.css.StylePropertyValue
import org.jetbrains.compose.web.css.vh

internal const val COLOR_MODE_KEY = "luki120.xyz-colorMode"

internal val ColorMode.Companion.SYSTEM
    get() = window.matchMedia("(prefers-color-scheme: dark)")

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        var colorMode by ColorMode.currentState

        LaunchedEffect(Unit) {
            ColorMode.SYSTEM.addEventListener("change", {
                colorMode = ColorMode.loadFromLocalStorage(key = COLOR_MODE_KEY) ?: ColorMode.systemPreference
            })
        }

        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}

@InitSilk
fun InitSilk(context: InitSilkContext) {
    context.config.initialColorMode = ColorMode.loadFromLocalStorage(key = COLOR_MODE_KEY) ?: ColorMode.systemPreference
    context.stylesheet.registerStyle("html") {
        cssRule(CSSMediaQuery.MediaFeature("prefers-reduced-motion", StylePropertyValue("no-preference"))) {
            Modifier.scrollBehavior(ScrollBehavior.Smooth)
        }
    }
    overrideSilkStyles(context = context)
}

private fun overrideSilkStyles(context: InitSilkContext) {
    context.theme.palettes.dark.button.apply {
        default = Colors.Transparent
        hover = Colors.Transparent
        pressed = Colors.Transparent
    }
    context.theme.palettes.light.button.apply {
        default = Colors.Transparent
        hover = Colors.Transparent
        pressed = Colors.Transparent
    }
    context.theme.modifyStyle(ButtonStyle) {
        base {
            Modifier.styleModifier {
                property("-webkit-tap-highlight-color", "transparent")
            }
        }
    }
    context.theme.modifyStyle(LinkStyle) {
        base {
            Modifier
                .backgroundColor(Colors.Transparent)
                .fontFamily("Barlow")
                .styleModifier {
                    property("-webkit-tap-highlight-color", "transparent")
                }
        }
    }
}
