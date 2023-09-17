val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
val bootRun: org.springframework.boot.gradle.tasks.run.BootRun by tasks
val jar: Jar by tasks

bootJar.enabled = false
bootRun.enabled = false
jar.enabled = true

plugins {
    kotlin("kapt") version "1.7.20"
}

dependencies {
}

allOpen {
}

kotlin.sourceSets.main {
    setBuildDir("$buildDir/generated/source/kapt/main")
}

tasks.register("prepareKotlinBuildScriptModel") {}
