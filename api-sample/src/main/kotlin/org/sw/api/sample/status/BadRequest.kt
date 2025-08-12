package org.sw.api.sample.status

import org.springframework.http.HttpStatusCode

class BadRequest: HttpStatusCode{

    override fun value(): Int {
        TODO("Not yet implemented")
    }

    override fun is1xxInformational(): Boolean {
        TODO("Not yet implemented")
    }

    override fun is2xxSuccessful(): Boolean {
        TODO("Not yet implemented")
    }

    override fun is3xxRedirection(): Boolean {
        TODO("Not yet implemented")
    }

    override fun is4xxClientError(): Boolean {
        TODO("Not yet implemented")
    }

    override fun is5xxServerError(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isError(): Boolean {
        TODO("Not yet implemented")
    }

}
