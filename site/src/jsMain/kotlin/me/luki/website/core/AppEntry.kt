package me.luki.website.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.setVariable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.layout.SurfaceVars.BackgroundColor
import com.varabyte.kobweb.silk.components.navigation.LinkStyle
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.plus
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.selectors.link
import com.varabyte.kobweb.silk.style.selectors.visited
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.button
import com.varabyte.kobweb.silk.theme.modifyStyle
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import me.luki.website.utils.CustomColors
import org.jetbrains.compose.web.css.CSSMediaQuery
import org.jetbrains.compose.web.css.StylePropertyValue
import org.jetbrains.compose.web.css.vh
import org.w3c.dom.MediaQueryListEvent

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
                localStorage.getItem(COLOR_MODE_KEY) ?: let { _ ->
                    colorMode = (if (it is MediaQueryListEvent && it.matches) ColorMode.DARK else ColorMode.LIGHT)
                }
            })
        }
        LaunchedEffect(colorMode) {
            document.body!!.setVariable(BackgroundColor, if (colorMode.isLight) Colors.White else Colors.Black)
        }

        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}

@InitSilk
fun InitSilk(context: InitSilkContext) {
    context.config.initialColorMode = localStorage.getItem(COLOR_MODE_KEY)?.let { ColorMode.valueOf(it) }
        ?: ColorMode.SYSTEM.let { query ->
            if (query.matches) ColorMode.DARK else ColorMode.LIGHT
        }
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
        (Breakpoint.ZERO + hover) {
            Modifier.textDecorationLine(TextDecorationLine.None)
        }
        (Breakpoint.MD + hover) {
            Modifier.textDecorationLine(TextDecorationLine.Underline)
        }
        link {
            Modifier.color(CustomColors.Purple)
        }
        visited {
            Modifier.color(CustomColors.Purple)
        }
    }
}
