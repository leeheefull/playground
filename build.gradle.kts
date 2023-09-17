import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.7" apply false
    id("io.spring.dependency-management") version "1.1.0"
    id("org.asciidoctor.convert") version "1.5.8" apply false

    kotlin("jvm") version "1.7.20"
    kotlin("plugin.spring") version "1.7.20" apply false
    kotlin("kapt") version "1.7.20" apply false
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
        implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
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
