package com.yeh35.bottlemessagebackend.dto

import com.yeh35.bottlemessagebackend.model.EmotionType

data class WriteBottleLetterDto(
    var userId : Long,
    var text: String,
    var emotion: EmotionType
)
