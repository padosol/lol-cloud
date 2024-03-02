package lol.cloud.lolcloud.s3.test

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class FolderTest {


    @Test
    fun 플더생성() {

        val file = File("D:/test1/")

        val flag = file.mkdir()

        Assertions.assertThat(true).isEqualTo(flag)

    }
}