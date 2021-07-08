package template.extensions

import com.kotlindiscord.kord.extensions.commands.converters.impl.defaultingCoalescingString
import com.kotlindiscord.kord.extensions.commands.converters.impl.defaultingString
import com.kotlindiscord.kord.extensions.commands.converters.impl.user
import com.kotlindiscord.kord.extensions.commands.parser.Arguments
import com.kotlindiscord.kord.extensions.commands.slash.AutoAckType
import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.utils.respond
import dev.kord.common.annotation.KordPreview
import template.TEST_SERVER_ID

@OptIn(KordPreview::class)
class TestExtension : Extension() {
    override val name = "test"

    override suspend fun setup() {
        command(::SlapArgs) {
            name = "slap"
            description = "Ask the bot to slap another user"

            check { event -> event.message.author != null }

            action {
                // Because of the DslMarker annotation KordEx uses, we need to grab Kord explicitly
                val kord = this@TestExtension.kord

                // Don't slap ourselves on request, slap the requester!
                val realTarget = if (arguments.target.id == kord.selfId) {
                    message.author!!
                } else {
                    arguments.target
                }

                message.respond("*slaps ${realTarget.mention} with their ${arguments.weapon}*")
            }
        }

        slashCommand(::SlapSlashArgs) {
            name = "slap"
            description = "Ask the bot to slap another user"

            // We want to send a public follow-up - KordEx will handle the rest
            autoAck = AutoAckType.PUBLIC

            guild(TEST_SERVER_ID)  // Otherwise it'll take an hour to update

            action {
                // Because of the DslMarker annotation KordEx uses, we need to grab Kord explicitly
                val kord = this@TestExtension.kord

                // Don't slap ourselves on request, slap the requester!
                val realTarget = if (arguments.target.id == kord.selfId) {
                    member
                } else {
                    arguments.target
                }

                publicFollowUp {
                    content = "*slaps ${realTarget?.mention} with their ${arguments.weapon}*"
                }
            }
        }
    }

    inner class SlapArgs : Arguments() {
        val target by user("target", description = "Person you want to slap")

        val weapon by defaultingCoalescingString(
            "weapon",

            defaultValue = "large, smelly trout",
            description = "What you want to slap with"
        )
    }

    inner class SlapSlashArgs : Arguments() {
        val target by user("target", description = "Person you want to slap")

        // Coalesced strings are not currently supported by slash commands
        val weapon by defaultingString(
            "weapon",

            defaultValue = "large, smelly trout",
            description = "What you want to slap with"
        )
    }
}
