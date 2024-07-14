package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

class EnvCommand(val variables: Map<String, String>) : DockerfileCommand() {
	override val keyword: String = "ENV"

	override fun toString(): String = buildString {
		append("$keyword ")

		variables.toList().joinToString(" ") { (key, value) ->
			"$key=\"${value.replace("\"", "\\\"")}\""
		}
	}
}
