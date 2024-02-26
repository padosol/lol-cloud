package lol.cloud.lolcloud.s3.bucket.controller

import lol.cloud.lolcloud.s3.bucket.domain.bucket_object.BucketObject
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.request.BucketObjectCreate
import lol.cloud.lolcloud.s3.bucket.dto.bucket_object.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket.service.BucketObjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/buckets")
class BucketObjectController(
    private val bucketObjectService: BucketObjectService,
) {

    /**
     *  객체 상세정보 && 중복체크
     */
    @GetMapping("/{bucketName}/objects/{objectName}")
    fun getObject(
        @PathVariable bucketName: String,
        @PathVariable objectName: String,
    ) : ResponseEntity<BucketObjectResponse>{

        val result = bucketObjectService.getObject(bucketName, objectName)

        return ResponseEntity(result, HttpStatus.OK)
    }


    /**
     * 객체 생성 컨트롤러
     */
    @PostMapping("/{bucketName}/objects")
    fun createObject(
        @PathVariable bucketName: String,
        @RequestBody bucketObjectCreate: BucketObjectCreate,
    ) : ResponseEntity<BucketObjectResponse>{

        val result: BucketObjectResponse = bucketObjectService.createObject(bucketName, bucketObjectCreate);

        return ResponseEntity(result, HttpStatus.CREATED)
    }

}