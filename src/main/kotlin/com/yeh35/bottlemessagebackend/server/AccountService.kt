package com.yeh35.bottlemessagebackend.server

import com.yeh35.bottlemessagebackend.dto.SigninDto
import com.yeh35.bottlemessagebackend.dto.SignupDto
import com.yeh35.bottlemessagebackend.model.Account
import com.yeh35.bottlemessagebackend.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    @Transactional
    fun signup(signupDto: SignupDto): Long {
        val byEmail = accountRepository.findByEmail(signupDto.email)

        if (byEmail.isPresent) {
            return -1L
        }

        val account = Account()
        account.email = signupDto.email
        account.name = signupDto.name
        account.password = signupDto.password
        val savedAccount = accountRepository.save(account)

        return savedAccount.id
    }

    fun signin(signupDto: SigninDto): Long {
        val byEmail = accountRepository.findByEmail(signupDto.email)

        return if (byEmail.isEmpty) {
            -1;
        } else {
            byEmail.get().id
        }
    }

}