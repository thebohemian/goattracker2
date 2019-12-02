group = "com.github.thebohemian.libgoat2"
version = "0.0.1"

plugins {
    `maven-publish`
    kotlin("jvm") version "1.3.61"
    id("org.jetbrains.kotlin.plugin.serialization") version ("1.3.61")
    `java-library`
}

repositories {

    mavenCentral()
    jcenter()
}

sourceSets {
    val example by creating {
        // The kotlin plugin will by default recognise Kotlin sources in src/tlib/kotlin
        compileClasspath += sourceSets["main"].output
        runtimeClasspath += sourceSets["main"].output
    }
}

configurations {
    val exampleImplementation by getting {
        extendsFrom(configurations["implementation"])
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("net.java.dev.jna:jna:4.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.14.0")
    implementation("com.charleskorn.kaml:kaml:0.15.0")

    runtime(kotlin("compiler"))
    runtime(kotlin("script-runtime"))
    runtime(kotlin("scripting-jvm"))
    runtime(kotlin("scripting-jvm-host"))
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("$buildDir/repository")
        }
    }
}

task<JavaExec>("runExample") {
    group = "other"
    description = "Runs example"
    classpath = sourceSets["example"].runtimeClasspath
    systemProperty("-Djna.library.path", "../../lib")
    main = "com.github.thebohemian.libgoat2.example.MainKt"
}
