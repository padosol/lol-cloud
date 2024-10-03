package lol.cloud.lolcloud.s3.bucket_object.infrastructure.adapters.input.rest.dto.response

data class BucketObjectDeleteResponse(
    var success: Int,
    var totalSize: Long,
    var fail: Int
) {
}