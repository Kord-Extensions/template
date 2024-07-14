package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

abstract class CmdCommand : DockerfileCommand() {
	class Exec(val instructions: Array<out String>) : CmdCommand() {
		override val keyword: String = "CMD"

		override fun toString(): String = buildString {
			append("$keyword [ ")

			append(
				instructions.joinToString(", ") {
					"\"$it\""
				}
			)

			append(" ]")
		}
	}

	class Shell(val instructions: String) : CmdCommand() {
		override val keyword: String = "CMD"

		override fun toString(): String = buildString {
			append("$keyword $instructions")
		}
	}
}
