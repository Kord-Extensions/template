import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application

	kotlin("jvm")
	kotlin("plugin.serialization")

	id("com.github.johnrengelman.shadow")
	id("io.gitlab.arturbosch.detekt")
}

group = "template"
version = "1.0-SNAPSHOT"

repositories {
	google()
	mavenCentral()

	maven {
		name = "Sonatype Snapshots (Legacy)"
		url = uri("https://oss.sonatype.org/content/repositories/snapshots")
	}

	maven {
		name = "Sonatype Snapshots"
		url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
	}
}

dependencies {
	detektPlugins(libs.detekt)

	implementation(libs.kord.extensions)
	implementation(libs.kotlin.stdlib)
	implementation(libs.kx.ser)

	// Logging dependencies
	implementation(libs.groovy)
	implementation(libs.jansi)
	implementation(libs.logback)
	implementation(libs.logback.groovy)
	implementation(libs.logging)
}

application {
	mainClass.set("template.AppKt")
}

tasks.withType<KotlinCompile> {
	compilerOptions {
		freeCompilerArgs.add("-Xopt-in=kotlin.RequiresOptIn")

		jvmTarget.set(JvmTarget.JVM_21)
	}
}

tasks.jar {
	manifest {
		attributes(
			"Main-Class" to "template.AppKt"
		)
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}

detekt {
	buildUponDefaultConfig = true

	config.from(rootProject.files("detekt.yml"))
}
