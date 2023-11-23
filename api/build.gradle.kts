val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
val bootRun: org.springframework.boot.gradle.tasks.run.BootRun by tasks
val jar: Jar by tasks
val kotlinVersion = "1.7.20"

bootJar.enabled = true
bootRun.enabled = true
jar.enabled = false

dependencies {
    // module
    implementation(project(":domain"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    // log
    implementation("com.github.maricn:logback-slack-appender:1.6.1")
}

tasks.register("prepareKotlinBuildScriptModel") {}
