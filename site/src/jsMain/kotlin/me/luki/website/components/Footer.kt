package me.luki.website.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import me.luki.website.styles.SocialButtonStyle
import me.luki.website.utils.Constants
import me.luki.website.utils.Images
import me.luki.website.utils.toSitePalette
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.css.px

@Composable
fun Footer() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .gridRow(2, 3)
            .backgroundColor(ColorMode.current.toSitePalette().nearBackground)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .gap(0.5.cssRem)
                .fillMaxWidth()
                .padding(topBottom = 15.px)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                SocialButton(imageSource = Images.GITHUB, url = Constants.GITHUB_URL)
                SocialButton(imageSource = Images.TWITTER, url = Constants.TWITTER_URL)
            }

            SpanText(
                text = "© 2024 Luki120",
                modifier = Modifier
                    .color(Colors.Gray)
                    .fontFamily("Barlow")
            )
        }
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun SocialButton(imageSource: String, url: String) {
    val isLight = when (ColorMode.current) {
        ColorMode.LIGHT -> true
        ColorMode.DARK -> false
    }
    val context = rememberPageContext()

    Button(
        onClick = { context.router.navigateTo(url) },
        modifier = SocialButtonStyle.toModifier()
    ) {
        Image(
            src = imageSource,
            modifier = Modifier
                .styleModifier {
                    filter { if (isLight) invert(1) else invert(0) }
                }
        )
    }
}
