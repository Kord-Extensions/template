package dev.kordex.gradle.docker.file.commands

class ShellCommand(val command: Array<String>) : CmdCommand() {
	override val keyword: String = "SHELL"

	override fun toString(): String = buildString {
		append("$keyword [ ")

		append(
			command.joinToString(", ") {
				"\"$it\""
			}
		)

		append(" ]")
	}
}
