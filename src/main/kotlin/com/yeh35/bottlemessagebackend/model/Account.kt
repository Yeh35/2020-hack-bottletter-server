package com.yeh35.bottlemessagebackend.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "accounts")
@Entity
class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    var id: Long = 0
        protected set

    @Column
    var email: String = ""

    @Column
    var name: String = ""

    @Column
    var password: String = ""

}