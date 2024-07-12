package bank.dao;

import bank.domain.AccountChangeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountChangeEventDAO extends JpaRepository<AccountChangeEvent, Long> {
}
