package com.yeh35.bottlemessagebackend.dto

import com.yeh35.bottlemessagebackend.model.Bottle
import com.yeh35.bottlemessagebackend.model.BottleReview

data class ReadBottleDto(
    var bottle: Bottle,
    var review: List<BottleReview>,
) {
}