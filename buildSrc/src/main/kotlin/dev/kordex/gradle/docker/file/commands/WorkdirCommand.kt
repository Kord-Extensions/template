package dev.kordex.gradle.docker.file.commands

class WorkdirCommand(val dir: String) : CmdCommand() {
	override val keyword: String = "WORKDIR"

	override fun toString(): String = buildString {
		append("$keyword $dir")
	}
}
