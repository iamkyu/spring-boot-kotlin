package io.iamkyu.sbk.config

import io.iamkyu.sbk.domain.Article
import io.iamkyu.sbk.domain.ArticleRepository
import io.iamkyu.sbk.domain.User
import io.iamkyu.sbk.domain.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val user = userRepository.save(User("superfoobar", "foo", "bar"))

        articleRepository.save(Article(
                title = "hello world1",
                headline = "my first blogging",
                content = "content",
                author = user))

        articleRepository.save(Article(
                title = "hello world2",
                headline = "my second blogging",
                content = "content",
                author = user))
    }
}