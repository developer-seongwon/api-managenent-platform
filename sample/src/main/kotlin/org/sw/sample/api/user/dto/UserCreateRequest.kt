package org.sw.sample.api.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.sw.sample.node.Account

@JsonDeserialize(using = UserCreateRequest.Deserializer::class)
class UserCreateRequest(

    @JsonProperty("account")
    val account: Account
) {

    class Deserializer : JsonDeserializer<UserCreateRequest>() {
        override fun deserialize(parser: JsonParser?, context: DeserializationContext?): UserCreateRequest? {
            // validate-null
            return context?.let { c -> parser?.let { p -> parser(p, c) } }
        }

        private fun parser(parser: JsonParser, context: DeserializationContext): UserCreateRequest {
            val root = parser.codec.readTree<JsonNode>(parser)

            val account = (root.get("account")?.let { it -> context.readTreeAsValue(it, Account::class.java) })
                ?.let { it ->
                    it.name ?: throw JsonParseException("'account.name' is required")
                    it.age ?: throw JsonParseException("'account.age' is required")
                    it
                } ?: throw JsonParseException("'account' is required")

            return UserCreateRequest(account = account)
        }
    }
}
