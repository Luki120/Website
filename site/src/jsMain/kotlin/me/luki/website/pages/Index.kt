package me.luki.website.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.gridTemplateRows
import com.varabyte.kobweb.core.Page
import me.luki.website.components.Footer
import me.luki.website.components.Header
import me.luki.website.sections.ExperienceSection
import me.luki.website.sections.MainSection
import org.jetbrains.compose.web.css.fr

@Page
@Composable
fun HomePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .gridTemplateRows { size(1.fr); size(auto) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Header()
            MainSection()
            ExperienceSection()
        }
        Footer()
    }
}
