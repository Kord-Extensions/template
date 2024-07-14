package dev.kordex.gradle.docker.file

class EmptyLine : DockerfileCommand() {
	override val keyword: String = ""

	override fun toString(): String =
		""
}
