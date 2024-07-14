package dev.kordex.gradle.docker

import java.io.File

class DockerfileBuilder {
	lateinit var target: File
	lateinit var dockerFile: Dockerfile

	fun file(path: String) {
		target = File(path)
	}
}
