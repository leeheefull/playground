val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
val bootRun: org.springframework.boot.gradle.tasks.run.BootRun by tasks
val jar: Jar by tasks
val querydslVersion = "5.0.0"
val mysqlVersion = "8.0.27"

bootJar.enabled = false
bootRun.enabled = false
jar.enabled = true

plugins {
    val kotlinVersion = "1.7.20"

    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

repositories {
}

dependencies {
    // DB
    implementation("mysql:mysql-connector-java:$mysqlVersion")

    // ORM
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-jpa:$querydslVersion")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

kotlin.sourceSets.main {
    setBuildDir("$buildDir/generated/source/kapt/main")
}

tasks.register("prepareKotlinBuildScriptModel") {}
