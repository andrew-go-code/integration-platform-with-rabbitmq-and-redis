plugins {
    kotlin("jvm") version "1.6.10"
    id("io.gatling.gradle") version "3.7.4"
}


dependencies{
    implementation("io.gatling:gatling-app:3.7.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
}

repositories {
    mavenCentral()
}
