package org.sw.api.sample.api.user.service

import org.sw.api.sample.status.Conflict
import org.sw.api.sample.status.InternalServerError
import org.sw.api.sample.status.NotFound

class UserResult<T>(
    val status: Int,
    val result: T? = null,
    val error: Map<String, Object>? = null,
) {

    companion object {
        fun <T> Ok(result: T): UserResult<T> {
            return UserResult(200, result)
        }

        fun error(status: Int, title: String, detail: String? = null): UserResult<Map<String, Object>> {
            return UserResult(
                status = status,
                result = mapOf(
                    "status" to status,
                    "title" to title,
                    "detail" to detail,
                ).filterValues { it != null })
        }

        fun <T> BadRequest(message: String, detail: String? = null): UserResult<T> {
            return error(400, message, detail)
        }

        fun <T> NotFound(message: String, detail: String? = null): UserResult<T> {
            return error(404, message, detail)
        }

        fun <T> Conflict(message: String, detail: String? = null): UserResult<T> {
            return error(409, message, detail)
        }

        fun <T> InternalServerError(message: String? = null, detail: String? = null): UserResult<T> {
            return error(500, "Internal Server Error", detail)
        }
    }
}