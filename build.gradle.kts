//import org.gradle.api.artifacts.Configuration

plugins {
    java
}
//
//val testImplementationWithResolve: Configuration = configurations.create("testImplementationWithResolve") {
//    isCanBeResolved = true
//    extendsFrom(configurations.getByName("testImplementation"))
//}
group = "org.com.kg.black_jack"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
    testImplementation("org.jmockit:jmockit:1.49")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("-javaagent:" + configurations.testRuntimeClasspath.get().find { it.name.contains("jmockit") }?.absolutePath);
}
