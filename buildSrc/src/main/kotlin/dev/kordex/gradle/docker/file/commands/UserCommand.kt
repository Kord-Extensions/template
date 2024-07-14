package dev.kordex.gradle.docker.file.commands

class UserCommand(val user: String, val group: String? = null) : CmdCommand() {
	override val keyword: String = "USER"

	override fun toString(): String = buildString {
		append("$keyword $user")

		if (group != null) {
			append(":$group")
		}
	}
}
