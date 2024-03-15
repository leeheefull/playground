import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val springBootVersion = "2.7.7"
    val kotlinVersion = "1.7.20"

    id("org.springframework.boot") version springBootVersion apply false
    id("io.spring.dependency-management") version "1.1.0"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
    kotlin("kapt") version kotlinVersion apply false
}

allprojects {
    group = "com.leeheefull"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    dependencies {
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib"))
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
