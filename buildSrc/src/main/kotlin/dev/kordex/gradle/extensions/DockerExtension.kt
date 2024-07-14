package dev.kordex.gradle.extensions

import dev.kordex.gradle.docker.file.CommandList
import java.io.File

open class DockerExtension {
	lateinit var target: File
	lateinit var commandsBuilder: CommandList.() -> Unit

	var generateOnBuild: Boolean = true

	val directives: MutableMap<String, String> = mutableMapOf()

	fun commands(body: CommandList.() -> Unit) {
		commandsBuilder = body
	}

	fun directive(key: String, value: String) {
		directives[key] = value
	}

	fun file(input: File) {
		target = input
	}
}
