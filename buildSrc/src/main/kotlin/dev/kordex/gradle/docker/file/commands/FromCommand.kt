package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

class FromCommand(
	val image: String,
	val alias: String? = null,
	val platform: String? = null,
) : DockerfileCommand() {
	override val keyword: String = "FROM"

	override fun toString(): String = buildString {
		append("$keyword ")

		if (platform != null) {
			append("--platform=$platform ")
		}

		append(image)

		if (alias != null) {
			append(" AS $alias")
		}
	}
}
