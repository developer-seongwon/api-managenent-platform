package org.sw.sample.api

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.sw.sample.api.CommonException
import java.net.URI

class GlobalExceptionHandler {
    @ExceptionHandler(CommonException::class)
    fun handleCommonException(servlet: HttpServletRequest, exception: CommonException): ResponseEntity<ProblemDetail> {
        return ProblemDetail.forStatus(exception.status).apply {
            this.instance = URI.create(servlet.requestURI)
            this.title = exception.title
            this.detail = exception.detail
            this.properties = exception.properties
        }.let { it ->
            ResponseEntity.status(exception.status)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(it)
        }
    }
}
