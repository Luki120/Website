package me.luki.website.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import me.luki.website.utils.toSitePalette
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

@Composable
fun Language(name: String) {
    Box(
        modifier = Modifier
            .width(130.px)
            .height(60.px)
            .border(
                color = ColorMode.current.toSitePalette().accent,
                style = LineStyle.Solid,
                width = 1.px
            ),
        contentAlignment = Alignment.Center
    ) {
        SpanText(
            text = name,
            modifier = Modifier
                .color(ColorMode.current.toSitePalette().accent)
                .fontSize(18.px)
                .fontFamily("Barlow")
                .textAlign(TextAlign.Center)
        )
    }
}
