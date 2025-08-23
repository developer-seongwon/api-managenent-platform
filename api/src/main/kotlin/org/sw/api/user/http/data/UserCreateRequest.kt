package org.sw.api.user.http.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.sw.api.sample.api.user.node.Account

class UserCreateRequest(
    @JsonProperty("account")
    @JsonDeserialize(using = AccountDeserializer::class)
    val account: Account,
) {

    class AccountDeserializer : JsonDeserializer<Account>() {
        override fun deserialize(parser: JsonParser?, context: DeserializationContext?): Account? {
            // validate-null
            return context?.let { c -> parser?.let { p -> parser(p, c) } }
        }

        private fun parser(parser: JsonParser, context: DeserializationContext): Account? {
            // 1단계: 타입 검증
            val account = parser.codec.readValue(parser, Account::class.java)

            // 2단계: 유효성 검증,, id는 PathVariable로 들어오기 때문에 Service레이어에서 검증
            require(account.name != null) { "이름은 필수입니다" }
            require(account.age != null) { "나이는 필수입니다" }

            return account
        }
    }
}
