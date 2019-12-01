group = "com.github.thebohemian.libgoat2"
version = "0.0.1"

plugins {
    `maven-publish`
    kotlin("jvm") version "1.3.61"
    `java-library`
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("net.java.dev.jna:jna:4.5.1")

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

task<JavaExec>("runi") {
    // TODO: Does not work properly yet (cannot be interactive)
    group = "other"
    description = "Run Interactive"
    classpath = sourceSets["main"].runtimeClasspath
    systemProperty("-Djna.library.path", "../../lib")
    main = "org.jetbrains.kotlin.cli.jvm.K2JVMCompiler"
    args = listOf("-no-stdlib")
}
