package org.sw.sample.api

import org.springframework.http.HttpStatus

class CommonException(
    val status: HttpStatus,
    val title: String? = null,
    val detail: String? = null,
    val properties: Map<String, Any>? = null,

    cause: Throwable? = null,
) : Exception(cause) {

    companion object {

        fun notFound(cause: Throwable? = null, block: Builder.() -> Unit): CommonException {
            return Builder(HttpStatus.NOT_FOUND, cause).apply(block).build()
        }

        fun conflict(cause: Throwable? = null, block: Builder.() -> Unit): CommonException {
            return Builder(HttpStatus.CONFLICT, cause).apply(block).build()
        }

        fun internalServerError(cause: Throwable? = null, block: Builder.() -> Unit): CommonException {
            return Builder(HttpStatus.INTERNAL_SERVER_ERROR, cause).apply(block).build()
        }
    }

    class Builder(private val status: HttpStatus, private val cause: Throwable?) {
        var title: String? = null
        var detail: String? = null
        var properties: Map<String, Any>? = null

        fun build() = CommonException(status, title, detail, properties, cause)
    }
}