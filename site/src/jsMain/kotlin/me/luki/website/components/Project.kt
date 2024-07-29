package me.luki.website.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import me.luki.website.styles.ProjectStyle
import me.luki.website.utils.Constants
import me.luki.website.utils.toSitePalette
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px

@Composable
fun Project(name: String, description: String) {
    val context = rememberPageContext()

    Box(
        contentAlignment = Alignment.Center,
        modifier = ProjectStyle.toModifier()
            .border(
                color = ColorMode.current.toSitePalette().accent,
                style = LineStyle.Solid,
                width = 2.px
            )
            .cursor(Cursor.Pointer)
            .onClick {
                context.router.navigateTo("${Constants.GITHUB_URL}/$name")
            }
            .padding(topBottom = 0.1.cssRem)
            .width(260.px)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .gap(0.2.cssRem)
                .padding(1.cssRem),
            verticalArrangement = Arrangement.Center
        ) {
            SpanText(
                text = name,
                modifier = Modifier
                    .color(ColorMode.current.toSitePalette().accent)
                    .fontSize(1.5.cssRem)
                    .fontFamily("Barlow")
                    .textAlign(TextAlign.Left)
            )
            SpanText(
                text = description,
                modifier = Modifier
                    .color(Colors.LightGray)
                    .fontSize(0.95.cssRem)
                    .fontFamily("Barlow")
                    .textAlign(TextAlign.Left)
            )
        }
    }
}
