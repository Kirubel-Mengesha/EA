package bank;

import java.util.Collection;

import bank.dao.AccountDAO;
import bank.dao.IAccountDAO;
import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.jms.IJMSSender;
import bank.jms.JMSSender;
import bank.logging.ILogger;
import bank.logging.Logger;
import bank.service.AccountService;
import bank.service.CurrencyConverter;
import bank.service.IAccountService;
import bank.service.ICurrencyConverter;


public class Application {
	public static void main(String[] args) {

		IAccountDAO accountDAO = new AccountDAO();
		ICurrencyConverter currencyConverter = new CurrencyConverter();
		IJMSSender jmsSender = new JMSSender();
		ILogger logger = new Logger();
		IAccountService accountService = new AccountService(accountDAO, currencyConverter,jmsSender, logger);
		// create 2 accounts;
		accountService.createAccount(1263862, "Frank Brown");
		accountService.createAccount(4253892, "John Doe");
		//use account 1;
		accountService.deposit(1263862, 240);
		accountService.deposit(1263862, 529);
		accountService.withdrawEuros(1263862, 230);
		//use account 2;
		accountService.deposit(4253892, 12450);
		accountService.depositEuros(4253892, 200);
		accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");
		// show balances
		
		Collection<Account> accountlist = accountService.getAllAccounts();
		Customer customer = null;
		for (Account account : accountlist) {
			customer = account.getCustomer();
			System.out.println("Statement for Account: " + account.getAccountnumber());
			System.out.println("Account Holder: " + customer.getName());
			System.out.println("-Date-------------------------"
							+ "-Description------------------"
							+ "-Amount-------------");
			for (AccountEntry entry : account.getEntryList()) {
				System.out.printf("%30s%30s%20.2f\n", entry.getDate()
						.toString(), entry.getDescription(), entry.getAmount());
			}
			System.out.println("----------------------------------------"
					+ "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
					account.getBalance());
		}
	}

}


