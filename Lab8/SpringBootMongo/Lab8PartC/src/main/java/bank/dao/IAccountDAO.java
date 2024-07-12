package bank.dao;

import java.util.Collection;

import bank.domain.Account;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface IAccountDAO extends MongoRepository<Account, Long> {

}
