package org.sw.sample.api.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.sw.sample.node.Account

@JsonDeserialize(using = UserReadRequest.Deserializer::class)
class UserReadRequest(
    @JsonProperty("account")
    val account: Account? = null
) {
    class Deserializer : JsonDeserializer<UserReadRequest>() {
        override fun deserialize(parser: JsonParser?, context: DeserializationContext?): UserReadRequest? {
            // validate-null
            return context?.let { c -> parser?.let { p -> parser(p, c) } }
        }

        private fun parser(parser: JsonParser, context: DeserializationContext): UserReadRequest {
            return UserReadRequest(parser.codec.readValue(parser, Account::class.java))
        }
    }
}
