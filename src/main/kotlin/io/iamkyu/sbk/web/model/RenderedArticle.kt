package io.iamkyu.sbk.web.model

import io.iamkyu.sbk.domain.User

data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String)
