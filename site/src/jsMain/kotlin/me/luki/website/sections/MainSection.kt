package me.luki.website.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.CSSLengthNumericValue
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowDown
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import me.luki.website.utils.CustomColors
import org.jetbrains.compose.web.css.*

val JumpKeyframe = Keyframes {
    from { Modifier.translateY(0.px) }
    to { Modifier.translateY(10.px) }
}

@Composable
fun MainSection() {
    About()
}

@Composable
private fun About() {
    val pageContext = rememberPageContext()

    Column(
        modifier = Modifier
            .id("about")
            .height("100svh".unsafeCast<CSSLengthNumericValue>())
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.position(Position.Relative)
        ) {
            SpanText(
                text = "Hi I'm Luki120",
                modifier = AboutStyle.toModifier()
                    .align(Alignment.CenterHorizontally)
                    .color(CustomColors.Purple)
                    .textAlign(TextAlign.Center)
                    .fontFamily("Quicksand")
            )
            SpanText(
                text = "iOS developer with +3 years of experience",
                modifier = DescriptionStyle.toModifier()
                    .align(Alignment.CenterHorizontally)
                    .color(Colors.LightGray)
                    .textAlign(TextAlign.Center)
                    .fontFamily("Barlow")
            )
        }
        Button(
            onClick = {
                pageContext.router.navigateTo("#experience")
            },
            modifier = Modifier
                .animation(
                    JumpKeyframe.toAnimation(
                        duration = 1.s,
                        iterationCount = AnimationIterationCount.Infinite,
                        timingFunction = AnimationTimingFunction.EaseOut,
                        direction = AnimationDirection.AlternateReverse
                    )
                )
                .color(CustomColors.Purple)
                .backgroundColor(Colors.Transparent)
                .bottom(10.percent)
                .position(Position.Absolute)
                .styleModifier {
                    property("-webkit-tap-highlight-color", "transparent")
                }
        ) {
            FaArrowDown(modifier = Modifier.fontSize(35.px))
        }
    }
}

val AboutStyle = CssStyle {
    Breakpoint.ZERO {
        Modifier.fontSize(55.px)
    }
    Breakpoint.MD {
        Modifier.fontSize(100.px)
    }
}

val DescriptionStyle = CssStyle {
    Breakpoint.ZERO {
        Modifier.fontSize(18.px)
    }
    Breakpoint.MD {
        Modifier.fontSize(30.px)
    }
}
