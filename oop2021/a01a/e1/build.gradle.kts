plugins {
    java
    application
    id("org.danilopianini.gradle-java-qa") version "1.74.0"
}

tasks.javadoc {
    isFailOnError = false
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // Use JUnit Jupiter API for testing.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")

    // Use JUnit Jupiter Engine for testing.
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.3")
}

val mainClass: String by project

application {
    // The following allows to run with: ./gradlew -PmainClass=it.unibo.oop.MyMainClass run
    mainClass.set(project.properties["mainClass"].toString())
}

val test by tasks.getting(Test::class) {
    // Use junit platform for unit tests
    useJUnitPlatform()
    testLogging {
        events(*(org.gradle.api.tasks.testing.logging.TestLogEvent.values())) // events("passed", "skipped", "failed")
    }
    testLogging.showStandardStreams = true    
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")// Use the latest version
}
