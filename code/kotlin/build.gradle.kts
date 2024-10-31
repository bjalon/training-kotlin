plugins {
    kotlin("jvm") version "2.0.20"
}

dependencies {
    val mockkVersion = "1.13.13"
    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect:2.0.20")

    testImplementation(kotlin("test"))

    testImplementation("io.mockk:mockk:${mockkVersion}")
    testImplementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(22)
}