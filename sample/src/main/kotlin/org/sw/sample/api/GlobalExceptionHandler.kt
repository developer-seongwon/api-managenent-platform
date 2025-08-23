package org.sw.sample.api

import com.fasterxml.jackson.core.JsonParseException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.MediaType
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.net.URI

@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * 서비스 로직에서 발생하는 예외 담당
     */
    @ExceptionHandler(CommonException::class)
    fun handleCommonException(servlet: HttpServletRequest, exception: CommonException): ResponseEntity<ProblemDetail> {
        return ProblemDetail.forStatus(exception.status).apply {
            this.instance = URI.create("${servlet.requestURI}")
            this.title = exception.title
            this.detail = exception.detail
        }.let { it ->
            ResponseEntity.status(exception.status)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(it)
        }
    }

    /**
     * Json 분석 과정에서 발생하는 예외 담당
     */
    @ExceptionHandler(JsonParseException::class)
    fun handleJsonException(servlet: HttpServletRequest, exception: JsonParseException): ResponseEntity<ProblemDetail> {
        return ProblemDetail.forStatus(400).apply {
            this.instance = URI.create("${servlet.requestURI}")
            this.title = "Invalid Json Format"
            this.detail = exception.message
        }.let { it ->
            ResponseEntity.status(400)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(it)
        }
    }

}
