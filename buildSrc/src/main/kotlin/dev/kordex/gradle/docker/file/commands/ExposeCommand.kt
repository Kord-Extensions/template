package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

class ExposeCommand(
	val port: Int,
	val protocol: Protocol = Protocol.TCP,
	val comment: String? = null
) : DockerfileCommand() {
	override val keyword: String = "EXPOSE"

	override fun toString(): String = buildString {
		append("$keyword $port/$protocol")

		if (comment != null) {
			append(" # $comment")
		}
	}

	sealed class Protocol(val protocol: String) {
		override fun toString(): String = protocol

		object TCP : Protocol("tcp")
		object UDP : Protocol("udp")
	}
}
