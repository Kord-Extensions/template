package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

class OnBuildCommand(
	val command: DockerfileCommand
) : DockerfileCommand() {
	override val keyword: String = "ONBUILD"

	override fun toString(): String {
		when (command) {
			is CopyCommand -> if (command.options.any { it is CopyCommand.Option.From }) {
				error("ONBUILD doesn't support COPY commands using --from.")
			}

			is FromCommand -> error("ONBUILD doesn't support FROM commands.")
			is OnBuildCommand -> error("ONBUILD doesn't support chaining ONBUILD commands.")
		}

		return buildString {
			append("$keyword $command")
		}
	}
}
