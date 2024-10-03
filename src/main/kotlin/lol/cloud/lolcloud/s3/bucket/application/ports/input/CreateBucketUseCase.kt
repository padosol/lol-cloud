package lol.cloud.lolcloud.s3.bucket.application.ports.input

import jakarta.servlet.http.HttpServletRequest
import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.response.BucketResponse

interface CreateBucketUseCase {
    fun createBucket(request: HttpServletRequest, bucket: Bucket): BucketResponse
}