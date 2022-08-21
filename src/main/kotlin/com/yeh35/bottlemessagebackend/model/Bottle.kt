package com.yeh35.bottlemessagebackend.model

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "bottle")
@Entity
class Bottle {

    @Id
    @GeneratedValue
    @Column(name = "bottle_id")
    var id: Long = 0
        protected set

    @ManyToOne
    @JoinColumn(name = "account_id")
    var account: Account?

    @Column(length = 255)
    var text: String = ""

    @Column
    var writeDate: LocalDateTime = LocalDateTime.now()

    @Column
    var readCount: Long = 0

    @Enumerated(EnumType.STRING)
    var emotion: EmotionType = EmotionType.NEUTRAL

    @Enumerated(EnumType.STRING)
    var status: BottleStatus = BottleStatus.OUTPOUR

    protected constructor() {
        account = null
    }

    constructor(account: Account, text: String, emotionType: EmotionType) {
        this.account = account
        this.text = text
        this.writeDate = LocalDateTime.now()
    }
}