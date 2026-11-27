plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
}

group = "pt.ua.estga.ocorrencias"
version = "1.0.0"
application {
    mainClass = "pt.ua.estga.ocorrencias.ApplicationKt"
}

dependencies {
    api(project(":shared:core"))
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
}