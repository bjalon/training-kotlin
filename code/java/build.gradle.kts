plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":code:kotlin"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(22)
}