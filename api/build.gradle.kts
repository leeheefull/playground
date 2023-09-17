val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
val bootRun: org.springframework.boot.gradle.tasks.run.BootRun by tasks
val jar: Jar by tasks

bootJar.enabled = true
bootRun.enabled = true
jar.enabled = false

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation ("org.jetbrains.kotlin:kotlin-reflect:1.7.20")
}

tasks.register("prepareKotlinBuildScriptModel") {}
