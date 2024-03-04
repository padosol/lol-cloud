package lol.cloud.lolcloud.s3.bucket.controller

import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.service.BucketObjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/objects")
class BucketObjectController(
    private val bucketObjectService: BucketObjectService,
) {

    /**
     *  객체 상세정보 && 중복체크
     */
    @GetMapping()
    fun getObject(
        @ModelAttribute bucketObjectRequest: BucketObjectRequest,
    ) : ResponseEntity<BucketObjectResponse>{

        val result = bucketObjectService.getObject(bucketObjectRequest)

        return ResponseEntity(result, HttpStatus.OK)
    }

    @GetMapping("/list")
    fun getObjectAll(
        @ModelAttribute bucketObjectRequest: BucketObjectRequest,
    ) : ResponseEntity<List<BucketObjectResponse>>? {

        val result: List<BucketObjectResponse> = bucketObjectService.getObjectAll(bucketObjectRequest)

        return null;
    }




}