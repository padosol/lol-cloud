package lol.cloud.lolcloud.common.`object`.controller

import jakarta.servlet.http.HttpServletRequest
import lol.cloud.lolcloud.common.`object`.dto.request.BucketObjectRequest
import lol.cloud.lolcloud.common.`object`.service.ObjectService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ObjectController(
    private val objectService: ObjectService,
) {

    @GetMapping("/**")
    fun getFile(
        request: HttpServletRequest,
    ): String {

        objectService.getFile(request.requestURI)

        return request.requestURI
    }


    @PutMapping("/bucket/object")
    fun createObject(
        @RequestBody bucketObjectRequest: BucketObjectRequest
    ) {

    }

}