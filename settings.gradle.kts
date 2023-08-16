pluginManagement {
	plugins {
		// Update this in libs.version.toml when you change it here.
		kotlin("jvm") version "1.9.0"
		kotlin("plugin.serialization") version "1.9.0"

		// Update this in libs.version.toml when you change it here.
		id("io.gitlab.arturbosch.detekt") version "1.23.1"

		id("com.github.jakemarsden.git-hooks") version "0.0.1"
		id("com.github.johnrengelman.shadow") version "8.1.1"
	}
}

rootProject.name = "template"
