package me.luki.website.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.cssRem

val AboutStyle = CssStyle {
    Breakpoint.ZERO {
        Modifier.fontSize(3.5.cssRem)
    }
    Breakpoint.MD {
        Modifier.fontSize(6.5.cssRem)
    }
}

val DescriptionStyle = CssStyle {
    Breakpoint.ZERO {
        Modifier.fontSize(1.1.cssRem)
    }
    Breakpoint.MD {
        Modifier.fontSize(1.90.cssRem)
    }
}
