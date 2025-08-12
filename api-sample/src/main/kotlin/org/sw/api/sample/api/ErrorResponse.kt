package org.sw.api.sample.api

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
open class ErrorResponse{

    @JsonProperty("type")
    val type: String? = null

    @JsonProperty("title")
    val title: String? = null

    @JsonProperty("status")
    val status: Int? = null

    @JsonProperty("detail")
    val detail: String? = null

    @JsonProperty("instance")
    val instance: String? = null
}
