package org.sw.sample.api.user.http

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.sw.sample.api.user.UserSpec
import org.sw.sample.api.user.http.data.UserCreateRequest
import org.sw.sample.api.user.http.data.UserCreateResponse
import org.sw.sample.api.user.http.data.UserReadRequest
import org.sw.sample.api.user.http.data.UserReadResponse
import org.sw.sample.api.user.node.Account
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
                ResponseEntity.status(200).contentType(APPLICATION_JSON).body(UserReadResponse(accounts = it))
            }
    }

    @PostMapping
    override fun readUsers(request: UserReadRequest): ResponseEntity<UserReadResponse> {
        return this.service.readUsers(account = request.account).let { it ->
            ResponseEntity.status(200)
                .contentType(APPLICATION_JSON)
                .body(UserReadResponse(accounts = it))
        }
    }

//    @GetMapping("/{id}")
//    override fun readUser(id: String): ResponseEntity<UserReadResponse> {
//        return this.service.readUser(Account(id)).let { it ->
//            ResponseEntity.status(it.status)
//                .contentType(APPLICATION_JSON)
//                .body(UserReadResponse(account = it.result))
//        }
//    }

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
}