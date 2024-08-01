import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link

plugins {
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kobweb.application)
}

group = "me.luki.website"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("My personal website, powered by Kobweb")
            head.add {
                link(rel = "stylesheet", href = "/fonts/faces.css")
            }
        }
    }
}

kotlin {
    configAsKobwebApplication("website")

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
        }

        jsMain.dependencies {
            implementation(compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
        }
    }
}
