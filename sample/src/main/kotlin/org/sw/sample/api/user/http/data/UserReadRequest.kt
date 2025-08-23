package org.sw.sample.api.user.http.data

import com.fasterxml.jackson.annotation.JsonProperty
import org.sw.sample.api.user.node.Account

class UserReadRequest(
    @JsonProperty("account")
    val account: Account? = null
)
