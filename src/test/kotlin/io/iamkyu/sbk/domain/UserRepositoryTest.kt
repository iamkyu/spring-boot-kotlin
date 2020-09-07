package io.iamkyu.sbk.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UserRepositoryTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository){

    @Test
    fun `When findByLogin then return User`() {
        // given
        val foobar = User("springfoobar", "Foo", "Bar")

        // when
        entityManager.persist(foobar)
        entityManager.flush()

        // then
        val user = userRepository.findByLogin(foobar.login)
        assertThat(user).isEqualTo(foobar)
    }
}