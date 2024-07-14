package dev.kordex.gradle.docker.file

abstract class DockerfileCommand {
	abstract val keyword: String

	abstract override fun toString(): String
}
