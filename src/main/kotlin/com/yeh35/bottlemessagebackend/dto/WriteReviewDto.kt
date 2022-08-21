package com.yeh35.bottlemessagebackend.dto

data class WriteReviewDto(
    var userId: Long,
    var bottleId: Long,
    var text: String
) {

}
