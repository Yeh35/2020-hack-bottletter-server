package com.yeh35.bottlemessagebackend.controller

import com.yeh35.bottlemessagebackend.dto.ReadBottleDto
import com.yeh35.bottlemessagebackend.dto.WriteBottleLetterDto
import com.yeh35.bottlemessagebackend.dto.WriteReviewDto
import com.yeh35.bottlemessagebackend.model.BottleStatus
import com.yeh35.bottlemessagebackend.model.EmotionType
import com.yeh35.bottlemessagebackend.server.BottleService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RequestMapping("/bottle")
@RestController
class BottleApi(
    private val bottleService: BottleService,
) {

    @GetMapping("/{emotion}")
    fun getBottle(@PathVariable emotion: String): Long {
        return bottleService.getBottle(EmotionType.valueOf(emotion))
    }

    @GetMapping("/read/{bottleId}")
    fun readBottle(@PathVariable bottleId: Long): ReadBottleDto {
        return bottleService.readBottle(bottleId)
    }

    @PostMapping
    fun writeBottle(
        @RequestBody letterDto: WriteBottleLetterDto,
    ) {
        bottleService.writeLetter(letterDto)
    }

    @PostMapping("/review")
    fun writeReview(
        @RequestBody writeReviewDto: WriteReviewDto,
    ) {
        bottleService.writeReview(writeReviewDto)
    }

    @PatchMapping("/down/{bottleId}")
    fun downBottle(
        @PathVariable bottleId: Long,
    ) {
        bottleService.downBottle(bottleId)
    }
}