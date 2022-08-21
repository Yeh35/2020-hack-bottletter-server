package com.yeh35.bottlemessagebackend.controller

import com.yeh35.bottlemessagebackend.dto.SigninDto
import com.yeh35.bottlemessagebackend.dto.SignupDto
import com.yeh35.bottlemessagebackend.server.AccountService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RequestMapping("/user")
@RestController
class UserApi(
    private val accountService: AccountService
) {

    @PostMapping("signup")
    fun signup(@RequestBody signupDto: SignupDto): HashMap<String, Any> {
        val id = accountService.signup(signupDto)

        val map = HashMap<String, Any>()
        map["userId"] = id
        return map
    }

    @PostMapping("signin")
    fun signin(@RequestBody signinDto: SigninDto): HashMap<String, Any> {
        val id = accountService.signin(signinDto)

        val map = HashMap<String, Any>()
        map["userId"] = id
        return map
    }

}