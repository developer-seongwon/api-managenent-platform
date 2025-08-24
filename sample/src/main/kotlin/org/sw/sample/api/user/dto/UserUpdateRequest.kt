package org.sw.sample.api.user.dto

import org.sw.sample.node.Account

class UserUpdateRequest(
    val account: Account? = null
) {
}