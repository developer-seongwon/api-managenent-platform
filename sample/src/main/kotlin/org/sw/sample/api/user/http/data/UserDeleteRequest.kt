package org.sw.sample.api.user.http.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.sw.sample.node.Account

@JsonDeserialize(using = UserDeleteRequest.Deserializer::class)
class UserDeleteRequest(
    @JsonProperty("account")
    val account: Account? = null
) {

    class Deserializer : JsonDeserializer<UserDeleteRequest>() {
        override fun deserialize(parser: JsonParser?, context: DeserializationContext?): UserDeleteRequest? {
            // validate-null
            return context?.let { c -> parser?.let { p -> parser(p, c) } }
        }

        private fun parser(parser: JsonParser, context: DeserializationContext): UserDeleteRequest? {
            val root = context.readTree(parser);

            val account = root.get("account")?.let { it -> context.readTreeAsValue(it, Account::class.java)}

            return UserDeleteRequest(account = account)
        }
    }
}