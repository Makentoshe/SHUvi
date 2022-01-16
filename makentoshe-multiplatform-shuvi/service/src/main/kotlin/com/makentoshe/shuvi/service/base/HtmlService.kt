package com.makentoshe.shuvi.service.base

import kotlinx.html.HTML

/** Service that returns a html as a response */
interface HtmlService: Service {
    fun html(): (HTML.() -> Unit)
}

