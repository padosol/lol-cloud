package lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import lol.cloud.lolcloud.s3.bucket.domain.service.BucketService
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket.request.BucketCreate
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket.request.BucketModify
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket.request.BucketRemove
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket.response.BucketResponse
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectCreate
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.domain.service.BucketObjectService
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.mapper.BucketMapper
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.mapper.BucketObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/buckets")
class BucketController(
    private val bucketObjectService: BucketObjectService,
    private val bucketService: BucketService
) {

    @GetMapping
    fun getBuckets() : ResponseEntity<List<BucketResponse>>{

        val result: List<BucketResponse> = bucketService.getBuckets()

        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * 버킷 조회
     */
    @GetMapping("/{bucketName}")
    fun getBucket(
        @PathVariable bucketName: String,
        @ModelAttribute(binding = false) bucketObjectSearch: BucketObjectRequest,
    ) : ResponseEntity<List<BucketObjectResponse>>{

        val result: List<BucketObjectResponse> = bucketService.getBucket(bucketName, bucketObjectSearch)

        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * 버킷 생성
     */
    @PostMapping()
    fun createBucket(
        @RequestBody @Valid bucketCreate: BucketCreate,
        request: HttpServletRequest
    ): ResponseEntity<BucketResponse> {

        val bucketResponse: BucketResponse = bucketService.createBucket(request, BucketMapper.toBucket(bucketCreate))

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


    /**
     * 객체 생성 컨트롤러
     */
    @PostMapping("/{bucketName}/objects/create-folder")
    fun createObject(
        @PathVariable bucketName: String,
        @RequestBody bucketObjectCreate: BucketObjectCreate,
    ) : ResponseEntity<BucketObjectResponse>{

        val result: BucketObjectResponse = bucketObjectService.createObject(
            BucketObjectMapper.toBucketObject(bucketName, bucketObjectCreate)
        )

        return ResponseEntity(result, HttpStatus.CREATED)
    }



}