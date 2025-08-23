package org.sw.sample.api.user.http.data

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.sw.sample.api.user.node.Account

class UserCreateResponse(
    @JsonProperty("account")
    @Schema
    val account: Account
)
