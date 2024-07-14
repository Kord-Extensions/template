package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

class LabelCommand : DockerfileCommand() {
	override val keyword: String = "LABEL"

	val labels: MutableMap<String, String> = mutableMapOf()

	fun label(key: String, value: String) {
		labels[key] = value
	}

	override fun toString(): String = buildString {
		append("$keyword ")

		labels.toList().joinToString(" ") { (key, value) ->
			"$key=\"${value.replace("\"", "\\\"")}\""
		}
	}
}
