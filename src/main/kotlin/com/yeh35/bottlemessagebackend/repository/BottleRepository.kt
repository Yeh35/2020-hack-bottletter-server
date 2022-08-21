package com.yeh35.bottlemessagebackend.repository

import com.yeh35.bottlemessagebackend.model.Bottle
import com.yeh35.bottlemessagebackend.model.BottleStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface BottleRepository : JpaRepository<Bottle, Long> {

    fun countByStatus(status: BottleStatus): Int
//
//    @Query("SELECT TOP 1 bottle_id FROM bottle WHERE status = :status ORDER BY writeDate LIMIT 1")
//    fun randomGet(status: BottleStatus) : Optional<Long>

    fun findFirst1ByStatusOrderByWriteDateDesc(status: BottleStatus) : Optional<Bottle>
}