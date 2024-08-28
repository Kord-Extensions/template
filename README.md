# KordEx Bot Template

This repository contains a basic KordEx example bot for you to use as a template for your own KordEx bots. This
includes the following:

- A basic extension that allows you to slap other people, using both chat commands and slash commands.
- A basic bot configuration that enables slash commands and shows you how to conditionally provide a different
  chat command prefix for different guilds.
- A Gradle Kotlin build script that uses the KordEx Gradle plugin and Detekt for linting (with a
  fairly strict configuration) – this uses Gradle 7's new version catalogue feature, for easy configuration of
  dependencies.
- GitHub CI scripts that build the bot and publish its artefacts.
- A reasonable `.gitignore` file, including one in the `.idea` folder that ignores files that you shouldn't commit –
  if you're using IDEA yourself, you should install the Ignore plugin to handle changes to this for you.
- A Groovy-based Logback config, so you have reasonable logging out of the box.
- Automatic generation of a Dockerfile via a `createDockerFile` task, also run at build time.

**Note:** This template includes a `.editorconfig` file that defaults to using tabs for indentation in almost all file
types. This is because tabs are more accessible for the blind, or those with impaired vision. We won't accept
feedback or PRs targeting this approach, though you can always change it in your projects.

## Potential Changes

- The `.yml` files in `.github/` are used to configure GitHub apps. If you're not using them, you can remove them.
- The provided `LICENSE` file contains The Unlicense, which makes this repository public domain. You will probably want
  to change this—we suggest looking at [Choose a License](https://choosealicense.com/) if you're not sure where to
  start.
- In the `build.gradle.kts`:
  - Set the `group` and `version` properties as appropriate.
  - In the `kordEx` and `tasks.jar` block, update the main class path/name as appropriate.
  - In the `kordEx` block, update the KordEx version if needed.
- In the `settings.gradle.kts`, update the name of the root project as appropriate.
- The bundled Detekt config is pretty strict—you can check over `detekt.yml` if you want to change it, but you need to
  follow the to-dos in that file regardless.
- The Logback configuration is in `src/main/resources/logback.groovy`. If the logging setup doesn't suit, you can change
  it there.

## Bundled Bot

- `App.kt` includes a basic bot, which uses environment variables (or variables in a `.env` file) for the testing guild
  ID (`TEST_SERVER`) and the bot's token (`TOKEN`). You can specify these either directly as environment variables, or
  as `KEY=value` pairs in a file named `.env`. Some example code is also included that shows one potential way of
  providing different command prefixes for different servers.
- `TestExtension.kt` includes an example extension that creates a `slap` command - this command works as both a
  message command and slash command, and allows you to slap other users with whatever you wish, defaulting to a
  `large, smelly trout`.

To test the bot, we recommend using a `.env` file that looks like the following:

```dotenv
TOKEN=abc...
TEST_SERVER=123...
```

Create this file, fill it out, and run the `run` gradle task for testing in development.

## Further Reading

To learn more about KordEx and how to work with it, [please browse the documentation](https://docs.kordex.dev).

For more information on the KordEx Gradle plugin and what you can do with it,
[please read this README](https://github.com/Kord-Extensions/gradle-plugins#kordex-plugin).
