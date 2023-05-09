import java.math.BigDecimal;

plugins {
    java
    jacoco
    application
}

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
    useJUnitPlatform();
    jvmArgs("-javaagent:" + configurations.testRuntimeClasspath.get().find { it.name.contains("jmockit") }?.absolutePath);
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = "com.kg.Main"
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

configure<JacocoPluginExtension> {
    toolVersion = "0.8.7" // Specify the desired JaCoCo version
}

application {
    mainClass.set("com.kg.Main")
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn("test")
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.named<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    dependsOn("jacocoTestReport")
    violationRules {
        rule {
            limit {
                minimum = BigDecimal.valueOf(0.8) // Set the desired minimum coverage percentage (e.g., 80%)
            }
        }
    }
}


