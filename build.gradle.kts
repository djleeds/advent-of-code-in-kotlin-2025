plugins {
    kotlin("jvm") version "2.2.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

dependencies {
    implementation("com.willowtreeapps.assertk:assertk:0.28.1")

}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }
}
