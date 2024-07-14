package me.luki.website.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import me.luki.website.utils.CustomColors
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

val LinkStyle = CssStyle {
    Breakpoint.MD {
        Modifier
            .border(
                color = CustomColors.Purple,
                style = LineStyle.Solid,
                width = 2.px
            )
            .borderRadius(20.px)
            .color(CustomColors.Purple)
            .padding(topBottom = 10.px, leftRight = 20.px)
    }
}
