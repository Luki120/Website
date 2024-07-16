package me.luki.website

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.setVariable
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.layout.SurfaceVars.BackgroundColor
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import org.jetbrains.compose.web.css.vh
import org.w3c.dom.MediaQueryListEvent

internal const val COLOR_MODE_KEY = "luki120.xyz-colorMode"

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        var colorMode by ColorMode.currentState
        LaunchedEffect(Unit) {
            window.matchMedia("(prefers-color-scheme: dark)").addEventListener("change", {
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
        ?: window.matchMedia("(prefers-color-scheme: dark)").let { query ->
            if (query.matches) ColorMode.DARK else ColorMode.LIGHT
        }
}
