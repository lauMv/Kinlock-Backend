plugins {
	java
	id("org.springframework.boot") version "3.5.5"
}

group = "com.app"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(enforcedPlatform("org.springframework.boot:spring-boot-dependencies:3.5.5"))

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-mail")

	// choose existing versions
	implementation("org.docx4j:docx4j-core:8.3.8")
	implementation("org.docx4j:docx4j-export-fo:8.3.15")
	implementation("org.docx4j:docx4j-JAXB-ReferenceImpl:8.3.8")
	implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
	implementation("org.glassfish.jaxb:jaxb-runtime:4.0.0")
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")

	compileOnly("org.projectlombok:lombok:1.18.34")
	annotationProcessor("org.projectlombok:lombok:1.18.34")

	runtimeOnly("org.postgresql:postgresql")

	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	developmentOnly("org.springframework.boot:spring-boot-devtools:3.5.5")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
