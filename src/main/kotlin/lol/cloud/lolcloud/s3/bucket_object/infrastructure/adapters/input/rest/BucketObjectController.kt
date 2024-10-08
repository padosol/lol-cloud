package lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest

import lol.cloud.lolcloud.s3.bucket_object.application.port.input.GetBucketObjectUseCase
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectDeleteResponse
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectResponse
import lol.cloud.lolcloud.s3.bucket_object.domain.BucketObjectService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/objects")
class BucketObjectController(
    private val bucketObjectService: BucketObjectService,
    private val getBucketObjectUseCase: GetBucketObjectUseCase
) {

    /**
     *  객체 상세정보
     */
    @GetMapping("/{bucketId}")
    fun getObject(
        @PathVariable("bucketId") bucketId: Long
    ): ResponseEntity<BucketObjectResponse>{

        val result = getBucketObjectUseCase.getObject(bucketId)

        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     *  폴더 객체 하위 리스트 가져오기
     */
    @GetMapping("/list")
    fun getObjectAll(
        @ModelAttribute bucketObjectRequest: BucketObjectRequest,
    ): ResponseEntity<List<BucketObjectResponse>>? {

        val result: List<BucketObjectResponse> = bucketObjectService.getObjectAll(bucketObjectRequest)

        return ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/list")
    fun removeAllBucketObject(
        @RequestBody bucketObjectList: List<BucketObjectRequest>
    ): ResponseEntity<BucketObjectDeleteResponse>{

        val bucketObjectDeleteResponse = bucketObjectService.removeObjectAll(bucketObjectList)

        return ResponseEntity(bucketObjectDeleteResponse, HttpStatus.OK)
    }


}