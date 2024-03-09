package template.extensions

import com.kotlindiscord.kord.extensions.commands.Arguments
import com.kotlindiscord.kord.extensions.commands.converters.impl.coalescingDefaultingString
import com.kotlindiscord.kord.extensions.commands.converters.impl.defaultingString
import com.kotlindiscord.kord.extensions.commands.converters.impl.user
import com.kotlindiscord.kord.extensions.components.components
import com.kotlindiscord.kord.extensions.components.publicButton
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.chatCommand
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.utils.respond
import template.TEST_SERVER_ID

class TestExtension : Extension() {
	override val name = "test"

	override suspend fun setup() {
		chatCommand(::SlapArgs) {
			name = "slap"
			description = "Ask the bot to slap another user"

			check { failIf(event.message.author == null) }

			action {
				// Don't slap ourselves on request, slap the requester!
				val realTarget = if (arguments.target.id == event.kord.selfId) {
					message.author!!
				} else {
					arguments.target
				}

				message.respond("*slaps ${realTarget.mention} with their ${arguments.weapon}*")
			}
		}

		chatCommand {
			name = "button"
			description = "A simple example command that sends a button."

			check { failIf(event.message.author == null) }

			action {
				message.respond {
					components {
						publicButton {
							label = "Button!"

							action {
								respond {
									content = "You pushed the button!"
								}
							}
						}
					}
				}
			}
		}

		publicSlashCommand(::SlapSlashArgs) {
			name = "slap"
			description = "Ask the bot to slap another user"

			guild(TEST_SERVER_ID)  // Otherwise it will take up to an hour to update

			action {
				// Don't slap ourselves on request, slap the requester!
				val realTarget = if (arguments.target.id == event.kord.selfId) {
					member
				} else {
					arguments.target
				}

				respond {
					content = "*slaps ${realTarget?.mention} with their ${arguments.weapon}*"
				}
			}
		}

		publicSlashCommand {
			name = "button"
			description = "A simple example command that sends a button."

			action {
				respond {
					components {
						publicButton {
							label = "Button!"

							action {
								respond {
									content = "You pushed the button!"
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
			name = "target"
			description = "Person you want to slap"
		}

		val weapon by coalescingDefaultingString {
			name = "weapon"

			defaultValue = "large, smelly trout"
			description = "What you want to slap with"
		}
	}

	inner class SlapSlashArgs : Arguments() {
		val target by user {
			name = "target"
			description = "Person you want to slap"
		}

		// Slash commands don't support coalescing strings
		val weapon by defaultingString {
			name = "weapon"

			defaultValue = "large, smelly trout"
			description = "What you want to slap with"
		}
	}
}
