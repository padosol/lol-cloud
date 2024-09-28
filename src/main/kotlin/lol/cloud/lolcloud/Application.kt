package lol.cloud.lolcloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LolCloudApplication

fun main(args: Array<String>) {
    runApplication<LolCloudApplication>(*args)
}