package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

class CopyCommand(
	val sources: Array<String>,
	val target: String,
) : DockerfileCommand() {
	override val keyword: String = "COPY"

	val options: MutableList<Option<*>> = mutableListOf()

	override fun toString(): String = buildString {
		append("$keyword ")

		if (options.isNotEmpty()) {
			append(
				options.joinToString(" ") {
					"${it.string}=${it.argument}"
				}
			)

			append(" ")
		}

		append("[ ")

		append(
			sources.joinToString(", ") {
				"\"$it\""
			}
		)

		append(", \"$target\"")

		append(" ]")
	}

	fun option(option: Option<*>) {
		options.add(option)
	}

	sealed class Option<T>(val string: String, val argument: T) {
		class Chmod(perms: Int) : Option<Int>("--chmod", perms)
		class Chown(user: String, group: String) : Option<String>("--chown", "$user:$group")
		class Exclude(path: String) : Option<String>("--exclude", path)
		class From(source: String) : Option<String>("--from", source)
		class Link : Option<Boolean>("--link", true)
		class Parents : Option<Boolean>("--parents", true)
	}
}
