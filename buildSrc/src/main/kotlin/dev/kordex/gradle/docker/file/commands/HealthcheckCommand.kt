package dev.kordex.gradle.docker.file.commands

import dev.kordex.gradle.docker.file.DockerfileCommand

abstract class HealthcheckCommand : DockerfileCommand() {
	class Check : HealthcheckCommand() {
		override val keyword: String = "HEALTHCHECK"

		lateinit var command: CmdCommand

		val options: MutableList<Option<*>> = mutableListOf()

		override fun toString(): String = buildString {
			append("$keyword ")

			if (options.isNotEmpty()) {
				append(
					options.joinToString(" ") {
						"${it.string}=${it.argument}"
					}
				)

				append("\\\n\t")
			}

			append(command)
		}

		fun cmdExec(instructions: Array<String>) {
			command = CmdCommand.Exec(instructions)
		}

		fun cmdShell(instructions: String) {
			command = CmdCommand.Shell(instructions)
		}

		fun option(option: Option<*>) {
			options.add(option)
		}

		sealed class Option<T>(val string: String, val argument: T) {
			class Interval(duration: String) : Option<String>("--interval", duration)
			class Timeout(duration: String) : Option<String>("--timeout", duration)
			class StartPeriod(duration: String) : Option<String>("--start-period", duration)
			class StartInterval(duration: String) : Option<String>("--start-interval", duration)
			class Retries(number: Int) : Option<Int>("--retries", number)
		}
	}

	class None : HealthcheckCommand() {
		override val keyword: String = "HEALTHCHECK"

		override fun toString(): String = buildString {
			append("$keyword NONE")
		}
	}

	class Builder {
		internal lateinit var command: HealthcheckCommand

		fun check(body: (Check).() -> Unit) {
			val newCommand = Check()

			body(newCommand)

			command = newCommand
		}

		fun none() {
			command = None()
		}

		fun build(): HealthcheckCommand =
			command
	}
}
