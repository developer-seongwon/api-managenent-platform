package org.sw.hub

import jakarta.annotation.PostConstruct
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.sw.hub.swagger.SwaggerFactory
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class HubManagement {

    @PostConstruct
    fun init() {
        println("Hub Management Service Started")

        val response = HttpClient.newHttpClient().send(
            HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .method(HttpMethod.GET.name(), HttpRequest.BodyPublishers.noBody())
                .uri(URI.create("http://localhost:8080/v3/api-docs")).build().also {
                    println(it)
                }, HttpResponse.BodyHandlers.ofString()
        )
//        val content = response.body()
//        println(content)
//        val doc = SwaggerFactory.parser(content)

        val doc = response.body().also { println(it) }.let { SwaggerFactory.parser(it) }


    }


}