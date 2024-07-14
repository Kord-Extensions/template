package dev.kordex.gradle.docker.file.commands

class StopSignalCommand(val signal: String) : CmdCommand() {
	override val keyword: String = "STOPSIGNAL"

	override fun toString(): String = buildString {
		append("$keyword $signal")
	}
}
