package lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest

import lol.cloud.lolcloud.s3.bucket_object.application.port.input.GetBucketObjectUseCase
import lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response.BucketObjectResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.ObjectInput

@RequestMapping("/api/buckets/{bucketId}")
@RestController
class BucketObjectV2Controller(
    private  val getBucketObjectUseCase: GetBucketObjectUseCase
) {

    @GetMapping("/objects")
    fun getBucketObjectCondition(
        @PathVariable("bucketId") bucketId: String,

    ): ResponseEntity<Void> {

        return ResponseEntity.ok(null)
    }

    @GetMapping("/objects/{objectId}")
    fun getBucketObject(
        @PathVariable("bucketId") bucketId: String,
        @PathVariable("objectId") objectId: String,
        @RequestParam(value = "prefix", required = false) prefix: String?
    ): ResponseEntity<BucketObjectResponse>{

        val response =
            getBucketObjectUseCase.findBucketObjectByBucketIdAndObjectIdAndPrefix(
                bucketId, objectId, prefix ?: ""
            )

        return ResponseEntity.ok(response)
    }

}