package lol.cloud.lolcloud.s3.bucket.application.ports.input.bucket

import jakarta.servlet.http.HttpServletRequest
import lol.cloud.lolcloud.s3.bucket.domain.Bucket
import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket.response.BucketResponse

interface CreateBucketUseCase {
    fun createBucket(request: HttpServletRequest, bucket: Bucket): BucketResponse
}