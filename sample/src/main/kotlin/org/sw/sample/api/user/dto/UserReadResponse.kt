package org.sw.sample.api.user.dto

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.v3.oas.annotations.media.Schema
import org.sw.sample.node.Account

@JsonInclude(JsonInclude.Include.NON_NULL)
class UserReadResponse(

    @Schema
    val account: Account? = null,

    @Schema
    val accounts: List<Account>? = null

){

}
