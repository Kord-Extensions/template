package dev.kordex.gradle

import dev.kordex.gradle.docker.Dockerfile
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class CreateDockerfileTask : DefaultTask() {
	@get:OutputFile
	lateinit var file: File

	@Internal
	val dockerFile: Dockerfile = Dockerfile()

	@TaskAction
	fun action() {
		file.writeText(dockerFile.toString())
	}
}
