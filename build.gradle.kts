plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("org.jetbrains.kotlin.kapt") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.1"
    id("io.micronaut.application") version "3.2.1"
}

version = "0.1"
group = "br.com.itau.bootcamp"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut:micronaut-http")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}



    application {
        mainClass.set("br.com.itau.bootcamp.ApplicationKt")
    }
    java {
        sourceCompatibility = JavaVersion.toVersion("11")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
        compileTestKotlin {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    graalvmNative.toolchainDetection.set(false)
    micronaut {
        runtime("netty")
        testRuntime("junit5")
        processing {
            incremental(true)
            annotations("br.com.itau.bootcamp.*")
        }
    }

//// https://mvnrepository.com/artifact/javax.inject/javax.inject
//compile group: 'javax.inject', name: 'javax.inject', version: '1'



