package org.sw.hub.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.parser.OpenAPIV3Parser

class SwaggerFactory {
    companion object {

        fun parser(value: String): OpenAPI {
            return OpenAPIV3Parser().readContents(value).also { result ->
                result.messages.takeIf { it.isNotEmpty() }?.let { println("파싱 경고/에러: $it") }
            }.openAPI
        }
    }
}