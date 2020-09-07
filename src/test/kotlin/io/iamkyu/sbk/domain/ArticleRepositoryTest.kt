package io.iamkyu.sbk.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class ArticleRepositoryTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        //given
        val foobar = User("springfoo", "foo", "bar")
        entityManager.persist(foobar)

        //when
        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", foobar)
        entityManager.persist(article)
        entityManager.flush()

        //then
        val found = articleRepository.findByIdOrNull(article.id!!) // dobule bang means will throw NPE if the value is null
        assertThat(found).isEqualTo(article)
    }
}