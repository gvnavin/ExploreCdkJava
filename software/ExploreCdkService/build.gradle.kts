import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    // https://github.com/johnrengelman/shadow
    // https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.gvnavin.service.explorecdk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://s3-us-west-2.amazonaws.com/dynamodb-local/release")
    }
    maven {
        url =
            uri("https://beta-com-gvnavin-codeartifacts-123124123445.d.codeartifact.ap-south-1.amazonaws.com/maven/beta-com-gvnavin-codeartifacts-repository/")
        credentials {
            username = "aws"
            password = System.getenv()["CODEARTIFACT_AUTH_TOKEN"]
        }
    }
}

dependencies {

    // https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-ses
    implementation("com.amazonaws:aws-java-sdk-ses:1.12.196")
    implementation("com.amazonaws:aws-java-sdk-lambda:1.12.196")

    // https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
    implementation("org.apache.poi:poi-ooxml:5.2.2")

    // https://mvnrepository.com/artifact/org.apache.poi/poi
    implementation("org.apache.poi:poi:5.2.2")

    // https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-dynamodb
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.12.196")

    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.9.0")

    // https://mvnrepository.com/artifact/org.apache.commons/commons-collections4
    implementation("org.apache.commons:commons-collections4:4.4")

    annotationProcessor("org.projectlombok:lombok:1.18.22")
    testCompileOnly("org.projectlombok:lombok:1.18.22")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.22")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.22")

    // https://mvnrepository.com/artifact/com.google.inject.extensions/guice-multibindings
    implementation("com.google.inject.extensions:guice-multibindings:4.2.3")

    // https://mvnrepository.com/artifact/com.google.inject/guice
    implementation("com.google.inject:guice:5.1.0")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-to-slf4j
    implementation("org.apache.logging.log4j:log4j-to-slf4j:2.17.2")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-jcl
    implementation("org.apache.logging.log4j:log4j-jcl:2.17.2")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-jcl
    implementation("org.apache.logging.log4j:log4j-jcl:2.17.2")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl
    testImplementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.2")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
    implementation("org.apache.logging.log4j:log4j-api:2.17.2")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api-kotlin
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.1.0")

    // https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/setup-project-gradle.html
    implementation(platform("software.amazon.awssdk:bom:2.17.162"))
    implementation("software.amazon.awssdk:dynamodb")
    implementation("software.amazon.awssdk:dynamodb-enhanced")
    implementation("software.amazon.awssdk:lambda")
    implementation("software.amazon.awssdk:dlm")
    implementation("software.amazon.awssdk:ses")


    // some java runtime mismatch issue.
    // https://mvnrepository.com/artifact/com.github.bijukunjummen/aws-sdk2-dynamo-json-helper
    // implementation 'com.github.bijukunjummen:aws-sdk2-dynamo-json-helper:0.11.0'

    // https://mvnrepository.com/artifact/software.amazon.lambda/powertools-tracing
    implementation("software.amazon.lambda:powertools-tracing:1.12.0")

    // https://mvnrepository.com/artifact/software.amazon.lambda/powertools-metrics
    implementation("software.amazon.lambda:powertools-metrics:1.12.0")

    // https://mvnrepository.com/artifact/com.amazonaws/aws-lambda-java-core
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")

    // https://mvnrepository.com/artifact/com.amazonaws/aws-lambda-java-events
    implementation("com.amazonaws:aws-lambda-java-events:3.11.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")


//  commented the sqllite4java libs in lib folder, since dependencies are not working
//  https://mvnrepository.com/artifact/com.almworks.sqlite4java/libsqlite4java-osx
//  testImplementation 'com.almworks.sqlite4java:libsqlite4java-osx:1.0.392'

//  https://mvnrepository.com/artifact/com.almworks.sqlite4java/sqlite4java
//  implementation 'com.almworks.sqlite4java:sqlite4java:1.0.392'

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("com.amazonaws:DynamoDBLocal:1.13.0") {
        exclude(module = "mockito-core")
    }

    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.6.10")

    // https://mvnrepository.com/artifact/junit/junit
    testImplementation("junit:junit:4.13.2")

    // https://mvnrepository.com/artifact/org.mockito/mockito-all
    testImplementation("org.mockito:mockito-all:1.10.19")

    // https://mvnrepository.com/artifact/org.powermock/powermock-api-mockito2
    testImplementation("org.powermock:powermock-api-mockito2:2.0.9")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}