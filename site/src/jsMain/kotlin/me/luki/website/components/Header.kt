package me.luki.website.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.CloseIcon
import com.varabyte.kobweb.silk.components.icons.HamburgerIcon
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.Overlay
import com.varabyte.kobweb.silk.components.overlay.OverlayVars
import com.varabyte.kobweb.silk.defer.deferRender
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import me.luki.website.styles.LinkStyle
import me.luki.website.styles.TranslucentNavBarStyle
import me.luki.website.utils.toSitePalette
import org.jetbrains.compose.web.css.*

val SideMenuSlideInAnimation = Keyframes {
    from { Modifier.translateX(100.percent) }
    to { Modifier }
}

private enum class SideMenuState {
    CLOSED,
    OPEN,
    CLOSING;

    fun close() = when (this) {
        CLOSED -> CLOSED
        OPEN -> CLOSING
        CLOSING -> CLOSING
    }
}

@Composable
fun Header() {
    deferRender {
        Row(
            TranslucentNavBarStyle.toModifier()
                .displayIfAtLeast(Breakpoint.MD)
                .gap(0.6.cssRem),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MenuItems()
            ColorModeButton()
        }
        Row(
            TranslucentNavBarStyle.toModifier()
                .displayUntil(Breakpoint.MD)
                .fontSize(0.8.cssRem)
                .gap(0.2.cssRem),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var menuState by remember { mutableStateOf(SideMenuState.CLOSED) }

            ColorModeButton()
            Button(
                modifier = Modifier.backgroundColor(Colors.Transparent),
                onClick = { menuState = SideMenuState.OPEN }
            ) {
                HamburgerIcon()
            }

            if (menuState != SideMenuState.CLOSED) {
                SideMenu(
                    menuState,
                    close = { menuState = menuState.close() },
                    onAnimationEnd = {
                        if (menuState == SideMenuState.CLOSING) menuState = SideMenuState.CLOSED
                    }
                )
            }
        }
    }
}

@Composable
private fun SideMenu(menuState: SideMenuState, close: () -> Unit, onAnimationEnd: () -> Unit) {
    Overlay(
        Modifier
            .setVariable(OverlayVars.BackgroundColor, Colors.Transparent)
            .onClick { close() }
    ) {
        key(menuState) {
            Column(
                Modifier
                    .align(Alignment.CenterEnd)
                    .animation(
                        SideMenuSlideInAnimation.toAnimation(
                            duration = 200.ms,
                            timingFunction = if (menuState == SideMenuState.OPEN) AnimationTimingFunction.EaseOut else AnimationTimingFunction.EaseIn,
                            direction = if (menuState == SideMenuState.OPEN) AnimationDirection.Normal else AnimationDirection.Reverse,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
                    .backgroundColor(ColorMode.current.toSitePalette().nearBackground)
                    .fillMaxHeight()
                    .gap(1.5.cssRem)
                    .width(clamp(8.cssRem, 33.percent, 10.cssRem))
                    .padding(top = 1.cssRem, leftRight = 1.cssRem)
                    .onClick { it.stopPropagation() }
                    .onAnimationEnd { onAnimationEnd() },
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    modifier = Modifier.backgroundColor(Colors.Transparent),
                    onClick = { close() })
                {
                    CloseIcon()
                }
                Column(
                    Modifier
                        .gap(1.5.cssRem)
                        .fontSize(1.2.cssRem)
                        .textAlign(TextAlign.Center),
                    horizontalAlignment = Alignment.End
                ) {
                    MenuItems()
                }
            }
        }
    }
}

@Composable
private fun MenuItems() {
    MenuLink(path = "#experience", text = "Experience")
    MenuLink(path = "#projects", text = "Projects")
}

@Composable
private fun MenuLink(path: String, text: String) {
    Link(
        path = path,
        text = text,
        modifier = LinkStyle.toModifier()
            .backgroundColor(Colors.Transparent)
            .fontFamily("Barlow")
            .zIndex(1)
            .styleModifier {
                property("pointer-events", "auto")
            },
        variant = UndecoratedLinkVariant.then(UncoloredLinkVariant)
    )
}

@Composable
private fun ColorModeButton() {
    var colorMode by ColorMode.currentState

    Button(
        onClick = { colorMode = colorMode.opposite },
        modifier = Modifier
            .backgroundColor(Colors.Transparent)
            .styleModifier {
                property("pointer-events", "auto")
            }
    ) {
        if (colorMode.isLight) MoonIcon() else SunIcon()
    }
}
