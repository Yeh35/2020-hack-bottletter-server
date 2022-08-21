package com.yeh35.bottlemessagebackend.repository

import com.yeh35.bottlemessagebackend.model.Bottle
import com.yeh35.bottlemessagebackend.model.BottleReview
import org.springframework.data.jpa.repository.JpaRepository

interface BottleReviewRepository : JpaRepository<BottleReview, Long> {

    fun findByBottle(bottle: Bottle): List<BottleReview>

}