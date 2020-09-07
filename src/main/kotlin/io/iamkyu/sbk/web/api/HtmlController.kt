package io.iamkyu.sbk.web.api

import io.iamkyu.sbk.domain.Article
import io.iamkyu.sbk.domain.ArticleRepository
import io.iamkyu.sbk.utils.format
import io.iamkyu.sbk.web.model.RenderedArticle
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(private val articleRepository: ArticleRepository) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "My First Blog"
        model["articles"] = articleRepository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        var article = articleRepository.findBySlug(slug)
                ?.render()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

        model["title"] = article.title
        model["article"] = article
        return "article"
    }


    fun Article.render() = RenderedArticle(
            slug,
            title,
            headline,
            content,
            author,
            addedAt.format()
    )
}