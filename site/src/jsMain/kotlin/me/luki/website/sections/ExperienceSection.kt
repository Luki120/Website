package me.luki.website.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import me.luki.website.components.Language
import me.luki.website.components.Project
import me.luki.website.utils.toSitePalette
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Composable
fun ExperienceSection() {
    Languages()
    Projects()
}

@Composable
private fun Languages() {
    Column(
        modifier = Modifier
            .id("experience")
            .classNames("section-container")
            .fillMaxSize()
            .height(50.vh)
            .scrollMargin(top = 5.cssRem),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SpanText(
            text = "Languages I know",
            modifier = Modifier
                .color(ColorMode.current.toSitePalette().accent)
                .fontSize(35.px)
                .fontFamily("Quicksand")
        )
        SimpleGrid(
            numColumns(1, md = 4),
            modifier = Modifier
                .gap(0.8.cssRem)
                .padding(top = 20.px)
        ) {
            Language(name = "Swift")
            Language(name = "Objective-C")
            Language(name = "Kotlin")
            Language(name = "Ruby")
        }
    }
}

@Composable
private fun Projects() {
    Column(
        modifier = Modifier
            .id("projects")
            .classNames("section-container")
            .fillMaxSize()
            .height(100.vh)
            .scrollMargin(top = 5.cssRem),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SpanText(
            text = "Projects",
            modifier = Modifier
                .color(ColorMode.current.toSitePalette().accent)
                .fontSize(35.px)
                .fontFamily("Quicksand")
        )
        SimpleGrid(
            numColumns(1, md = 3),
            modifier = Modifier
                .gap(0.8.cssRem)
                .padding(top = 20.px)
        ) {
            Project(name = "Areesha", description = "Keep track of your favorite TV shows")
            Project(name = "Azure", description = "FOSS TOTP 2FA with a clean, straightforward UI")
            Project(name = "Chrissa", description = "An actually good weather framework for jailbroken devices")
        }
    }
}
