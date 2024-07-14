package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

class ArgCommand(
	val key: String,
	val value: String? = null,
) : DockerfileCommand() {
	override val keyword: String = "ARG"

	override fun toString(): String = buildString {
		append("$keyword $key")

		if (value != null) {
			append("=$value")
		}
	}
}
