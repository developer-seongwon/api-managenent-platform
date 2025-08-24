package org.sw.sample.api.user

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.sw.sample.api.user.dto.UserCreateRequest
import org.sw.sample.api.user.dto.UserCreateResponse
import org.sw.sample.api.user.dto.UserDeleteRequest
import org.sw.sample.api.user.dto.UserDeleteResponse
import org.sw.sample.api.user.dto.UserReadRequest
import org.sw.sample.api.user.dto.UserReadResponse
import org.sw.sample.api.user.dto.UserUpdateRequest
import org.sw.sample.api.user.dto.UserUpdateResponse

@Tag(name = "User API", description = "회원 관련 API")
interface UserSpec {

    @Operation(
        summary = "모든 회원 조회",
        description = "모든 회원 정보를 조회합니다.",
        parameters = [Parameter(
            `in` = ParameterIn.HEADER,
            name = "content-type",
            required = true,
            schema = Schema(type = "string", allowableValues = arrayOf("application/json")),
        )]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "회원 조회 성공",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserCreateResponse::class)
                )]
            )
        ]
    )
    fun readUsers(): ResponseEntity<UserReadResponse>

    @Operation(
        summary = "조건 회원 조회",
        description = "조건에 일치하는 회원 정보를 조회합니다.",
        parameters = [Parameter(
            `in` = ParameterIn.HEADER,
            name = "content-type",
            required = true,
            schema = Schema(type = "string", allowableValues = arrayOf("application/json")),
        )]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "회원 조회 성공",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserCreateResponse::class)
                )]
            )
        ]
    )
    fun readUsers(@RequestBody request: UserReadRequest?): ResponseEntity<UserReadResponse>

    @Operation(
        summary = "회원 조회",
        description = "아이디를 사용하여 회원 정보를 조회합니다.",
        parameters = [Parameter(
            `in` = ParameterIn.HEADER,
            name = "content-type",
            required = true,
            schema = Schema(type = "string", allowableValues = arrayOf("application/json")),
        ),
            Parameter(
                `in` = ParameterIn.PATH,
                name = "id",
                required = true
            )
        ]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "회원 조회 성공",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserCreateResponse::class)
                )]
            )
        ]
    )
    fun readUser(@PathVariable id: String): ResponseEntity<UserReadResponse>

    @Operation(
        summary = "회원 등록",
        description = "회원 정보를 등록합니다.",
        parameters = [Parameter(
            `in` = ParameterIn.HEADER,
            name = "content-type",
            required = true,
            schema = Schema(type = "string", allowableValues = arrayOf("application/json")),
        ), Parameter(
            `in` = ParameterIn.PATH,
            name = "id",
            required = true,
            schema = Schema(type = "string")
        )]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "회원 등록 성공",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserCreateResponse::class)
                )]
            )]
    )
    fun createUser(
        @PathVariable id: String,
        @RequestBody request: UserCreateRequest
    ): ResponseEntity<UserCreateResponse>

    @Operation(
        summary = "회원 삭제",
        description = "회원 정보를 삭제합니다.",
        parameters = [Parameter(
            `in` = ParameterIn.HEADER,
            name = "content-type",
            required = true,
            schema = Schema(type = "string", allowableValues = arrayOf("application/json")),
        ), Parameter(
            `in` = ParameterIn.PATH,
            name = "id",
            required = true,
            schema = Schema(type = "string")
        )]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "회원 삭제 성공",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserCreateResponse::class)
                )]
            )]
    )
    fun deleteUser(
        @PathVariable id: String,
        @RequestBody request: UserDeleteRequest
    ): ResponseEntity<UserDeleteResponse>

    @Operation(
        summary = "회원 갱신",
        description = "회원 정보를 갱신합니다.",
        parameters = [Parameter(
            `in` = ParameterIn.HEADER,
            name = "content-type",
            required = true,
            schema = Schema(type = "string", allowableValues = arrayOf("application/json")),
        ), Parameter(
            `in` = ParameterIn.PATH,
            name = "id",
            required = true,
            schema = Schema(type = "string")
        )]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "회원 갱신 성공",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = UserUpdateResponse::class)
                )]
            )]
    )
    fun updateUser(
        @PathVariable id: String,
        @RequestBody request: UserUpdateRequest
    ): ResponseEntity<UserUpdateResponse>
}