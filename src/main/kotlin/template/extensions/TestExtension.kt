package template.extensions

import dev.kordex.core.commands.Arguments
import dev.kordex.core.commands.converters.impl.coalescingDefaultingString
import dev.kordex.core.commands.converters.impl.defaultingString
import dev.kordex.core.commands.converters.impl.user
import dev.kordex.core.components.components
import dev.kordex.core.components.publicButton
import dev.kordex.core.extensions.Extension
import dev.kordex.core.extensions.chatCommand
import dev.kordex.core.extensions.event
import dev.kordex.core.extensions.publicSlashCommand
import dev.kordex.core.i18n.withContext
import dev.kordex.core.utils.respond
import template.TEST_SERVER_ID
import template.i18n.Translations

class TestExtension : Extension() {
	override val name = "test"

	override suspend fun setup() {
		chatCommand(::SlapArgs) {
			name = Translations.Commands.Slap.name
			description = Translations.Commands.Slap.description

			check { failIf(event.message.author == null) }

			action {
				// Don't slap ourselves on request, slap the requester!
				val realTarget = if (arguments.target.id == event.kord.selfId) {
					message.author!!
				} else {
					arguments.target
				}

				message.respond(
					Translations.Commands.Slap.response
						.withContext(this)
						.translateNamed(
							"target" to realTarget.mention,
							"weapon" to arguments.weapon
						)
				)
			}
		}

		chatCommand {
			name = Translations.Commands.Button.name
			description = Translations.Commands.Button.description

			check { failIf(event.message.author == null) }

			action {
				message.respond {
					components {
						publicButton {
							label = Translations.Components.Button.label
								.withLocale(this@action.getLocale())

							action {
								respond {
									content = Translations.Components.Button.response
										.withLocale(getLocale())
										.translate()
								}
							}
						}
					}
				}
			}
		}

		publicSlashCommand(::SlapSlashArgs) {
			name = Translations.Commands.Slap.name
			description = Translations.Commands.Slap.description

			guild(TEST_SERVER_ID)  // Otherwise it will take up to an hour to update

			action {
				// Don't slap ourselves on request, slap the requester!
				val realTarget = if (arguments.target.id == event.kord.selfId) {
					member
				} else {
					arguments.target
				}

				respond {
					content = Translations.Commands.Slap.response
						.withContext(this@action)
						.translateNamed(
							"target" to realTarget?.mention,
							"weapon" to arguments.weapon
						)
				}
			}
		}

		publicSlashCommand {
			name = Translations.Commands.Button.name
			description = Translations.Commands.Button.description

			action {
				respond {
					components {
						publicButton {
							label = Translations.Components.Button.label
								.withLocale(this@action.getLocale())

							action {
								respond {
									content = Translations.Components.Button.response
										.withLocale(getLocale())
										.translate()
								}
							}
						}
					}
				}
			}
		}
	}

	inner class SlapArgs : Arguments() {
		val target by user {
			name = Translations.Arguments.Target.name
			description = Translations.Arguments.Target.description
		}

		val weapon by coalescingDefaultingString {
			name = Translations.Arguments.Weapon.name

			defaultValue = "🐟"
			description = Translations.Arguments.Weapon.description
		}
	}

	inner class SlapSlashArgs : Arguments() {
		val target by user {
			name = Translations.Arguments.Target.name
			description = Translations.Arguments.Target.description
		}

		// Slash commands don't support coalescing strings
		val weapon by defaultingString {
			name = Translations.Arguments.Weapon.name

			defaultValue = "🐟"
			description = Translations.Arguments.Weapon.description
		}
	}
}
