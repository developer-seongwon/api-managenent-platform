package org.sw.api.sample.api

import jakarta.servlet.http.HttpServletRequest

import org.springframework.http.MediaType
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.net.URI


@RestControllerAdvice
class CommonExceptionHandler {

    @ExceptionHandler(CommonException::class)
    fun handleCommonException(
        request: HttpServletRequest,
        exception: CommonException
    ): ResponseEntity<ProblemDetail> {
        exception.printStackTrace()
        return ProblemDetail.forStatus(exception.status).apply {
            this.instance = URI.create(request.requestURI)
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