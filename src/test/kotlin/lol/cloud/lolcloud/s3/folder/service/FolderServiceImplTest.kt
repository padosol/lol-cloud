package lol.cloud.lolcloud.s3.folder.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.io.File

@SpringBootTest
@ActiveProfiles("test")
class FolderServiceImplTest @Autowired constructor(
    private val folderService: FolderService,
) {


    @Test
    fun 폴더생성() {

        // given && when
        folderService.createFolder("bucket","","")
        folderService.createFolder("bucket", "test/", "")

        // then

        val file = File("D:/bucket/test")

        Assertions.assertThat(true).isEqualTo(file.isDirectory)
    }

}
