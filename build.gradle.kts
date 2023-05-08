plugins {
    id("java")
    id("jacoco")
}

group = "org.com.kg.black_jack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
// JUnit 5 dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    // JMockit dependency
    testImplementation("org.jmockit:jmockit:1.49")
}



tasks.test {
    useJUnitPlatform()
}