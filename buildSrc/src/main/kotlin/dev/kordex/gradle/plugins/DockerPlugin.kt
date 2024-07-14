package dev.kordex.gradle.plugins

import dev.kordex.gradle.CreateDockerfileTask
import dev.kordex.gradle.extensions.DockerExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register

class DockerPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		val extension = target.extensions.create<DockerExtension>("docker")

		val task = target.tasks.register<CreateDockerfileTask>("createDockerfile") {
			group = "generation"
			description = "Generate a Dockerfile, as configured."

			file = extension.target

			extension.commandsBuilder(dockerFile.commands)
			extension.directives.forEach(dockerFile.directives::set)
		}

		if (extension.generateOnBuild) {
			target.tasks.getByName("build") {
				finalizedBy(task)
			}
		}
	}
}
