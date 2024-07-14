package dev.kordex.gradle.docker.file.commands

class VolumeCommand(val volumes: Array<String>) : CmdCommand() {
	override val keyword: String = "VOLUME"

	override fun toString(): String = buildString {
		append("$keyword [ ")

		append(
			volumes.joinToString(", ") {
				"\"$it\""
			}
		)

		append(" ]")
	}
}
