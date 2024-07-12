package accounts.repository;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void whenFindByAccountHolder_thenReturnAccount() {
        // given
        Account kira = new Account("123", 123.2, "kira");
        entityManager.persist(kira);
        entityManager.flush();

        // when
        Account found = accountRepository.findByAccountHolder(kira.getAccountHolder());

        // then
        assertThat(found.getAccountHolder(), is(equalTo(kira.getAccountHolder())));
    }
}