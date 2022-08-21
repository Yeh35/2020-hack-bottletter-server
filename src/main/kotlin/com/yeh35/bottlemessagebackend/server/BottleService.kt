package com.yeh35.bottlemessagebackend.server

import com.yeh35.bottlemessagebackend.dto.ReadBottleDto
import com.yeh35.bottlemessagebackend.dto.WriteBottleLetterDto
import com.yeh35.bottlemessagebackend.dto.WriteReviewDto
import com.yeh35.bottlemessagebackend.model.Bottle
import com.yeh35.bottlemessagebackend.model.BottleReview
import com.yeh35.bottlemessagebackend.model.BottleStatus
import com.yeh35.bottlemessagebackend.model.EmotionType
import com.yeh35.bottlemessagebackend.repository.AccountRepository
import com.yeh35.bottlemessagebackend.repository.BottleRepository
import com.yeh35.bottlemessagebackend.repository.BottleReviewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BottleService(
    private val accountRepository: AccountRepository,
    private val bottleRepository: BottleRepository,
    private val bottleReviewRepository: BottleReviewRepository,
) {

    /**
     * 감정선에 따라 보틀을 하나 얻는다.
     */
    fun getBottle(emotion: EmotionType): Long {
        val countByStatus = bottleRepository.countByStatus(BottleStatus.OUTPOUR)

//        return bottleRepository.randomGet(status = BottleStatus.OUTPOUR).orElse(-1)
        val optional = bottleRepository.findFirst1ByStatusOrderByWriteDateDesc(status = BottleStatus.OUTPOUR)
        return if (optional.isPresent) {
            optional.get().id
        } else {
            -1
        }
    }

    @Transactional
    fun readBottle(bottleId: Long): ReadBottleDto {
        val bottle = bottleRepository.findById(bottleId).orElseThrow()
        bottle.readCount++
        bottle.status = BottleStatus.READING

        val reviewList = bottleReviewRepository.findByBottle(bottle)

        return ReadBottleDto(bottle, reviewList)
    }

    /**
     * 편지 쓰기
     */
    @Transactional
    fun writeLetter(
        letterDto: WriteBottleLetterDto,
    ) {
        val account = accountRepository.findById(letterDto.userId).orElseThrow()

        val bottle = Bottle(
            account = account,
            text = letterDto.text,
            emotionType = letterDto.emotion
        )

        bottleRepository.save(bottle)
    }


    /**
     * 편지 리뷰 쓰기
     */
    @Transactional
    fun writeReview(
        writeReviewDto: WriteReviewDto,
    ) {
        val account = accountRepository.findById(writeReviewDto.userId).orElseThrow()
        val bottle = bottleRepository.findById(writeReviewDto.bottleId).orElseThrow()

        bottle.status = BottleStatus.OUTPOUR

        if (writeReviewDto.text.isNotBlank()) {
            val bottleReview = BottleReview()

            bottleReview.writer = account
            bottleReview.bottle = bottle
            bottleReview.text = writeReviewDto.text

            bottleReviewRepository.save(bottleReview)
        }

    }

    @Transactional
    fun downBottle(bottleId: Long) {
        val bottle = bottleRepository.findById(bottleId).orElseThrow()
        bottle.status = BottleStatus.DOWN
    }
}