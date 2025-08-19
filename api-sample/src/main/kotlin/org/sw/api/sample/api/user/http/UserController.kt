package org.sw.api.sample.api.user.http

import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.sw.api.sample.api.user.UserSpec
import org.sw.api.sample.api.user.http.data.UserCreateRequest
import org.sw.api.sample.api.user.http.data.UserCreateResponse
import org.sw.api.sample.api.user.http.data.UserReadRequest
import org.sw.api.sample.api.user.http.data.UserReadResponse
import org.sw.api.sample.api.user.node.Account
import org.sw.api.sample.api.user.service.UserService

@RestController
@RequestMapping("/users")
class UserController(
    val service: UserService
) : UserSpec {

    @GetMapping
    override fun readUser(): ResponseEntity<UserReadResponse> {
        return this.service.readUsers()
            .let { it -> ResponseEntity.status(200).contentType(APPLICATION_JSON).body(UserReadResponse(accounts = it)) }
    }

//    @PostMapping
//    override fun readUser(request: UserReadRequest): ResponseEntity<UserReadResponse> {
//        return this.service.readUsers(account = request.account).let { it ->
//            ResponseEntity.status(it.status)
//            .contentType(APPLICATION_JSON)
//            .body(with(it) {
//                when (status) {
//                    200 -> UserReadResponse(accounts = result)
//                    else -> UserReadResponse(accounts = result)
//                }
//            })
////            }.statusUserReadResponse(accounts = it.result))
////            else -> ResponseEntity.status(it.status)
////            .contentType(APPLICATION_JSON)
////            .body(UserReadResponse(accounts = it.result))
////            when (it.status) {
////                200 ->  ResponseEntity.status(it.status)
////                    .contentType(APPLICATION_JSON)
////                    .body(UserReadResponse(accounts = it.result))
////                else -> ResponseEntity.status(it.status)
////                    .contentType(APPLICATION_JSON)
////                    .body(UserReadResponse(accounts = it.result))
////            }
//        }
//    }
//
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
            .let { it -> ResponseEntity.status(200)
                    .contentType(APPLICATION_JSON)
                    .body(UserCreateResponse(account = it))
            }
    }
}