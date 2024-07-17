package me.luki.website.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import org.jetbrains.compose.web.css.px

val SocialButtonStyle = CssStyle(
    extraModifier = Modifier
        .height(28.px)
        .width(28.px)
) {
    hover {
        Modifier.scale(1.1)
    }
}
