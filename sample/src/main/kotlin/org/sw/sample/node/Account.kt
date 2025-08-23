package org.sw.sample.node

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "회원 계정 정보")
@JsonDeserialize(using = Account.Deserializer::class)
data class Account(
    @Schema(name = "회원 아이디")
    val id: String? = null,
    @Schema(name = "회원 이름")
    val name: String? = null,
    @Schema(name = "회원 나이")
    val age: Int? = null
) {

    class Deserializer : JsonDeserializer<Account>() {
        override fun deserialize(parser: JsonParser?, context: DeserializationContext?): Account? {
            return context?.let { c -> parser?.let { p -> parser(p, c) } }
        }

        private fun parser(parser: JsonParser, context: DeserializationContext): Account? {
            val node = parser.codec.readTree<JsonNode>(parser)

            val id = extractString(node, "id", parser)
            val name = extractString(node, "name", parser)
            val age = extractInt(node, "age", parser)
            return Account(id, name, age)
        }

        private fun extractString(node: JsonNode, name: String, parser: JsonParser): String? {
            return node.get(name)?.let { it ->
                when {
                    it.isNull -> null
                    it.isTextual -> it.asText()
                    else -> throw JsonParseException(parser, "'account.${name}' only textual: ${it}")
                }
            }
        }

        private fun extractInt(node: JsonNode, name: String, parser: JsonParser): Int? {
            return node.get(name)?.let { it ->
                when {
                    it.isNull -> null
                    it.isInt -> it.asInt()
                    else -> throw JsonParseException(parser, "'account.${name} only int: ${it}")
                }
            }
        }
    }
}