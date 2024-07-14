package dev.kordex.gradle.docker

import dev.kordex.gradle.docker.file.CommandList

class Dockerfile {
	val directives: MutableMap<String, String> = mutableMapOf(
		"escape" to "\\",
		"syntax" to "docker/dockerfile:1"
	)

	val commands: CommandList = mutableListOf()

	fun commands(body: CommandList.() -> Unit) {
		body(commands)
	}

	override fun toString(): String = buildString {
		directives.forEach { (key, value) ->
			appendLine("# $key=$value")
		}

		appendLine()

		commands.forEach(::appendLine)
	}
}
