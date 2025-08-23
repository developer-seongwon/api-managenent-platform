package org.sw.sample.api.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.sw.sample.node.Account

class UserCreateResponse(
    @JsonProperty("account")
    @Schema
    val account: Account
)
