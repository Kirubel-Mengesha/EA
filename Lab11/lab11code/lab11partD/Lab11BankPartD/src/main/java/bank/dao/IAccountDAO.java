package bank.dao;

import java.util.Collection;

import bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAccountDAO extends JpaRepository<Account, Long> {

}
