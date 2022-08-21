package com.yeh35.bottlemessagebackend.model

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "bottle_review")
@Entity
class BottleReview() {

    @Id
    @GeneratedValue
    var id: Long = 0
        protected set

    @ManyToOne
    @JoinColumn(name = "account_id")
    lateinit var writer: Account

    @ManyToOne
    @JoinColumn(name = "bottle_id")
    lateinit var bottle: Bottle

    @Column
    var text: String = ""

    @Column
    var writeDate: LocalDateTime = LocalDateTime.now()

}