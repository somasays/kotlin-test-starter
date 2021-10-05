package common

import common.JsonMapper.defaultMapper
import io.restassured.RestAssured
import io.restassured.response.ResponseBodyExtractionOptions
import io.restassured.specification.RequestSpecification
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import org.junit.jupiter.api.BeforeAll

open class ServerTest {

    protected fun RequestSpecification.When(): RequestSpecification {
        return this.`when`()
    }

    protected inline fun <reified T> ResponseBodyExtractionOptions.to(): T {
        return defaultMapper.decodeFromString(this.asString())
    }

    protected inline fun <reified T> RequestSpecification.bodyJson(obj: T): RequestSpecification {
        return this.body(defaultMapper.encodeToString(obj))
    }

    companion object {

        @ExperimentalCoroutinesApi
        @BeforeAll
        @JvmStatic
        fun configureRestAssured() {
            RestAssured.baseURI = "https://google.com"
        }
    }


}
