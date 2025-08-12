package org.sw.api.sample.api.user.http.data

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.sw.api.sample.api.ErrorResponse
import org.sw.api.sample.api.user.http.UserResponse
import org.sw.api.sample.api.user.node.Account
import org.sw.api.sample.api.user.service.UserResult

@JsonInclude(JsonInclude.Include.NON_NULL)
class UserReadResponse(

    @Schema
    account: Account? = null,

    @Schema
    accounts: List<Account>? = null

) : UserResponse, ErrorResponse() {

    constructor(type: String?, title: String?, status: Int?, detail: String?, instance: String? = null) : this() {
        this.type = type
        this.title = title
        this.status = status
        this.detail = detail
        this.instance = instance
    }
}