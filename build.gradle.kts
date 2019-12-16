import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61" apply false
    `maven-publish`
}

allprojects {
    group = "org.jetbrains.dokka.examples.plugins"
    version = "0.11.0-SNAPSHOT"
}

subprojects {
    apply(plugin="org.jetbrains.kotlin.jvm")
    apply(plugin="org.gradle.maven-publish")

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }

    publishing {
        val maven by publications.creating(MavenPublication::class) {
            from(components["java"])
        }
    }
}
