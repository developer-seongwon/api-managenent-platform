package org.sw.sample.api.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.sw.sample.node.Account

data class UserDeleteResponse(
    @JsonProperty("account")
    val account: Account? = null
)
