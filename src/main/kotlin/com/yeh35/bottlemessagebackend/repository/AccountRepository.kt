package com.yeh35.bottlemessagebackend.repository

import com.yeh35.bottlemessagebackend.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AccountRepository : JpaRepository<Account, Long> {

    fun findByEmail(email: String): Optional<Account>

}