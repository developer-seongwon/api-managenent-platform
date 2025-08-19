package org.sw.api.sample.api.user.http.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.swagger.v3.oas.annotations.media.Schema
import org.sw.api.sample.api.user.http.data.UserCreateRequest.AccountDeserializer
import org.sw.api.sample.api.user.node.Account

class UserCreateResponse(
    @JsonProperty("account")
    @Schema
    val account: Account
)
