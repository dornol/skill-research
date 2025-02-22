import org.jooq.meta.jaxb.ForcedType
import org.jooq.meta.jaxb.Logging

val jooqVersion = "3.19.1"

plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    id("nu.studer.jooq") version "9.0"
}

group = "dev.dornol"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    jooqGenerator("org.postgresql:postgresql:42.7.2")
    jooqGenerator("org.jooq:jooq:${jooqVersion}")
    jooqGenerator("org.jooq:jooq-meta:${jooqVersion}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}


// ref: https://github.com/etiennestuder/gradle-jooq-plugin
jooq {
    configurations {
        create("main") {

            generateSchemaSourceOnCompilation.set(true)
            // true 로 설정 시 빌드(실행) 시 마다 generate 한다.
            // false 로 설정 시 수동으로 generate?


            jooqConfiguration.apply {
                logging = Logging.WARN
                jdbc.apply {
                    url = "jdbc:postgresql://localhost:5432/postgres"
                    user = "postgres"
                    password = "postgres"
//                    properties.add(Property().apply {
//                        key = "ssl"
//                        value = "true"
//                    })
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                        forcedTypes.addAll(
                            listOf(
                                ForcedType().apply {
                                    name = "varchar"
                                    includeExpression = ".*"
                                    includeTypes = "JSONB?"
                                },
                                ForcedType().apply {
                                    name = "varchar"
                                    includeExpression = ".*"
                                    includeTypes = "INET"
                                }
                            ))
                    }
                    generate.apply {
                        isJavaTimeTypes = true
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true  // 메서드 체이닝 가능한 setter
                        isInterfaces = true
                    }
                    target.apply {
                        packageName = "dev.dornol.jooq.mytarget"

                        directory = "build/generated/jooq"
                        // 생성전략
                        // 1. build 경로에 생성
                        // 2. src 밑에 생성해서 vcs에 commit 할 수도 있음.
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

