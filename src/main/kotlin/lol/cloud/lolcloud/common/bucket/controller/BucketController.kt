package lol.cloud.lolcloud.common.bucket.controller

import jakarta.validation.Valid
import lol.cloud.lolcloud.common.bucket.dto.request.BucketCreate
import lol.cloud.lolcloud.common.bucket.dto.request.BucketModify
import lol.cloud.lolcloud.common.bucket.dto.request.BucketRemove
import lol.cloud.lolcloud.common.bucket.dto.response.BucketResponse
import lol.cloud.lolcloud.common.bucket.service.BucketService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/buckets")
class BucketController(
    private val bucketService: BucketService,
) {


    /**
     * 버킷 생성
     */
    @PostMapping()
    fun createBucket(
        @RequestBody @Valid bucketCreate: BucketCreate,
    ): ResponseEntity<BucketResponse> {

        val bucketResponse: BucketResponse = bucketService.createBucket(bucketCreate.toEntity())

        return ResponseEntity(bucketResponse, HttpStatus.CREATED)
    }

    /**
     * 버킷 수정
     */
    @PutMapping
    fun modifyBucket(
        @RequestBody @Valid bucketModify: BucketModify,
    ) {

    }

    /**
     * 버킷 삭제
     */
    @DeleteMapping
    fun removeBucket(
        @RequestBody @Valid bucketRemove: BucketRemove,
    ) {

    }





}