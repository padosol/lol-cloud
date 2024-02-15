package lol.cloud.lolcloud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class LolCloudApplication

fun main(args: Array<String>) {
    runApplication<LolCloudApplication>(*args)
}