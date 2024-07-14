package dev.kordex.gradle.docker.file

import dev.kordex.gradle.annotations.DockerfileCommandDsl
import dev.kordex.gradle.docker.file.commands.*

typealias CommandList = MutableList<DockerfileCommand>

@DockerfileCommandDsl
fun CommandList.add(sources: Array<String>, target: String, body: (AddCommand.() -> Unit)? = null) {
	val command = AddCommand(sources, target)

	body?.invoke(command)
	add(command)
}

@DockerfileCommandDsl
fun CommandList.arg(key: String, value: String) {
	add(ArgCommand(key, value))
}

@DockerfileCommandDsl
fun CommandList.cmdExec(vararg instructions: String) {
	add(CmdCommand.Exec(instructions))
}

@DockerfileCommandDsl
fun CommandList.cmdShell(instructions: String) {
	add(CmdCommand.Shell(instructions))
}

@DockerfileCommandDsl
fun CommandList.comment(text: String) {
	add(Comment(text))
}

@DockerfileCommandDsl
fun CommandList.copy(source: String, target: String, body: (CopyCommand.() -> Unit)? = null) {
	val command = CopyCommand(arrayOf(source), target)

	body?.invoke(command)
	add(command)
}

@DockerfileCommandDsl
fun CommandList.copy(sources: Array<String>, target: String, body: (CopyCommand.() -> Unit)? = null) {
	val command = CopyCommand(sources, target)

	body?.invoke(command)
	add(command)
}

@DockerfileCommandDsl
fun CommandList.entryPointExec(vararg instructions: String) {
	add(EntrypointCommand.Exec(instructions))
}

@DockerfileCommandDsl
fun CommandList.entryPointShell(instructions: String) {
	add(EntrypointCommand.Shell(instructions))
}

@DockerfileCommandDsl
fun CommandList.emptyLine() {
	add(EmptyLine())
}

@DockerfileCommandDsl
fun CommandList.env(variables: Map<String, String>) {
	add(EnvCommand(variables))
}

@DockerfileCommandDsl
fun CommandList.expose(
	port: Int,
	protocol: ExposeCommand.Protocol = ExposeCommand.Protocol.TCP,
	comment: String? = null
) {
	add(ExposeCommand(port, protocol, comment))
}

@DockerfileCommandDsl
fun CommandList.from(
	image: String,
	alias: String? = null,
	platform: String? = null,
) {
	add(FromCommand(image, alias, platform))
}

@DockerfileCommandDsl
fun CommandList.healthcheck(body: (HealthcheckCommand.Builder).() -> Unit) {
	val builder = HealthcheckCommand.Builder()

	body(builder)
	add(builder.build())
}

@DockerfileCommandDsl
fun CommandList.label(body: LabelCommand.() -> Unit) {
	val command = LabelCommand()

	body(command)
	add(command)
}

@DockerfileCommandDsl
fun CommandList.onBuildCommand(body: CommandList.() -> Unit) {
	val commands: CommandList = mutableListOf()

	body(commands)

	add(OnBuildCommand(commands.first()))
}

@DockerfileCommandDsl
fun CommandList.runExec(vararg command: String, body: (RunCommand.Exec.() -> Unit)? = null) {
	val commandObj = RunCommand.Exec(command)

	body?.invoke(commandObj)
	add(commandObj)
}

@DockerfileCommandDsl
fun CommandList.runShell(command: String, body: (RunCommand.Shell.() -> Unit)? = null) {
	val commandObj = RunCommand.Shell(command)

	body?.invoke(commandObj)
	add(commandObj)
}

@DockerfileCommandDsl
fun CommandList.shell(command: Array<String>) {
	add(ShellCommand(command))
}

@DockerfileCommandDsl
fun CommandList.stopSignal(signal: String) {
	add(StopSignalCommand(signal))
}

@DockerfileCommandDsl
fun CommandList.user(user: String, group: String? = null) {
	add(UserCommand(user, group))
}

@DockerfileCommandDsl
fun CommandList.volume(volumes: Array<String>) {
	add(VolumeCommand(volumes))
}

@DockerfileCommandDsl
fun CommandList.workdir(dir: String) {
	add(WorkdirCommand(dir))
}
