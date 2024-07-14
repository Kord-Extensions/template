package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

object EntrypointCommand {
	class Exec(val instructions: Array<out String>) : DockerfileCommand() {
		override val keyword: String = "ENTRYPOINT"

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

	class Shell(val instructions: String) : DockerfileCommand() {
		override val keyword: String = "ENTRYPOINT"

		override fun toString(): String = buildString {
			append("$keyword $instructions")
		}
	}
}
