package bank.service;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class BankStatementPrinter {
    @Autowired
    private AccountService accountService;

    private Logger logger = LoggerFactory.getLogger(BankStatementPrinter.class);
    public void print(Collection<Account> accountlist){
        Customer customer = null;
        for (Account account : accountlist) {
            customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountnumber());
            System.out.println("Account Holder: " + customer.getName());
            System.out.println(
                    "-Date-------------------------" + "-Description------------------" + "-Amount-------------");
            for (AccountEntry entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n", entry.getDate().toString(), entry.getDescription(),
                        entry.getAmount());
            }
            System.out.println("----------------------------------------" + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
        }
        System.out.println("-----------------------------------------------------------------");

    }
}

