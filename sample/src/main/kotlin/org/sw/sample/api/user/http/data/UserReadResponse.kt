package org.sw.sample.api.user.http.data

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import org.sw.sample.api.user.node.Account

@JsonInclude(JsonInclude.Include.NON_NULL)
class UserReadResponse(

    @Schema
    val account: Account? = null,

    @Schema
    val accounts: List<Account>? = null

){

}
