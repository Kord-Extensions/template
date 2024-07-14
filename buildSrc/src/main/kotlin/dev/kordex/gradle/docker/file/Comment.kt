package dev.kordex.gradle.docker.file

class Comment(val text: String) : DockerfileCommand() {
	override val keyword: String = "#"

	override fun toString(): String =
		text.replace("\r\n", "\n")
			.split("\n")
			.map { "$keyword $it" }
			.joinToString("\n")
}
