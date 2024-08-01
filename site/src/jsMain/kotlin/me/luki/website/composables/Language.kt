package me.luki.website.composables

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import me.luki.website.utils.toSitePalette
import org.jetbrains.compose.web.css.*

@Composable
fun Language(name: String, skillLevel: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(
                color = ColorMode.current.toSitePalette().accent,
                style = LineStyle.Solid,
                width = 1.px
            )
            .padding(topBottom = 0.70.cssRem, leftRight = 1.5.cssRem)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(0.2.cssRem)
        ) {
            LanguageText(text = name, fontSize = 1.2.cssRem)
            LanguageText(text = skillLevel, fontSize = 0.90.cssRem, opacity = 0.6)
        }
    }
}

@Composable
private fun LanguageText(text: String, fontSize: CSSSizeValue<CSSUnit.rem>, opacity: Number = 1) {
    SpanText(
        text = text,
        modifier = Modifier
            .color(ColorMode.current.toSitePalette().accent)
            .fontSize(fontSize)
            .fontFamily("Barlow")
            .opacity(opacity)
    )
}
