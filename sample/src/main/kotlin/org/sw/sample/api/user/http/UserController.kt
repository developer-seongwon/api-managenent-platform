package org.sw.sample.api.user.http

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.sw.sample.api.user.UserSpec
import org.sw.sample.api.user.http.data.UserCreateRequest
import org.sw.sample.api.user.http.data.UserCreateResponse
import org.sw.sample.api.user.http.data.UserDeleteRequest
import org.sw.sample.api.user.http.data.UserDeleteResponse
import org.sw.sample.api.user.http.data.UserReadRequest
import org.sw.sample.api.user.http.data.UserReadResponse
import org.sw.sample.node.Account
import org.sw.sample.api.user.service.UserService

@RestController
@RequestMapping("/users")
class UserController(
    val service: UserService
) : UserSpec {

    @GetMapping
    override fun readUsers(): ResponseEntity<UserReadResponse> {
        return this.service.readUsers()
            .let { it ->
                ResponseEntity.status(200)
                    .contentType(APPLICATION_JSON)
                    .body(UserReadResponse(accounts = it))
            }
    }

    @PostMapping
    override fun readUsers(request: UserReadRequest?): ResponseEntity<UserReadResponse> {
        return (request?.let { it -> this.service.readUsers(it.account) } ?: this.service.readUsers())
            .let {
                ResponseEntity.status(200)
                    .contentType(APPLICATION_JSON)
                    .body(UserReadResponse(accounts = it))
            }
    }

    @GetMapping("/{id}")
    override fun readUser(@PathVariable id: String): ResponseEntity<UserReadResponse> {
        return Account(id = id).let { this.service.readUser(it) }
            .let {
                ResponseEntity.status(200)
                    .contentType(APPLICATION_JSON)
                    .body(UserReadResponse(account = it))
            }
    }

    @PostMapping("/{id}")
    override fun createUser(id: String, request: UserCreateRequest): ResponseEntity<UserCreateResponse> {
        return Account(id, request.account.name, request.account.age)
            .let { it -> this.service.createUser(it) }
            .let { it ->
                ResponseEntity.status(200)
                    .contentType(APPLICATION_JSON)
                    .body(UserCreateResponse(account = it))
            }
    }

    @DeleteMapping("/{id}")
    override fun deleteUser(id: String, request: UserDeleteRequest): ResponseEntity<UserDeleteResponse> {
        val account = request.account?.let { it -> Account(id = id, it.name, it.age) } ?: Account(id = id)
        return this.service.deleteUser(account)
            .let {
                ResponseEntity.status(200)
                    .contentType(APPLICATION_JSON)
                    .body(UserDeleteResponse(account = it))
            }
    }
}