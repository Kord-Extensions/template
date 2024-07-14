package dev.kordex.gradle

import dev.kordex.gradle.docker.Dockerfile

fun dockerfile(body: Dockerfile.() -> Unit): Dockerfile {
	val dockerfile = Dockerfile()

	body(dockerfile)

	return dockerfile
}
