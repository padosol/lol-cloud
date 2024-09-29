package lol.cloud.lolcloud.s3.file.controller

import lol.cloud.lolcloud.s3.bucket.infrastructure.adapters.input.web.dto.bucket_object.request.BucketObjectRequest
import lol.cloud.lolcloud.s3.file.service.FileService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api")
class FileController(
    private val fileService: FileService,
) {

    @PostMapping(value = ["/upload"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(
        file: MultipartFile,

        @ModelAttribute bucketObjectRequest: BucketObjectRequest,

        ) : ResponseEntity<String>{

        fileService.upload(bucketObjectRequest, file)

        return ResponseEntity("success", HttpStatus.OK);
    }

}