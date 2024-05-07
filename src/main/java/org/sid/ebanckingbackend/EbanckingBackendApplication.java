package org.sid.ebanckingbackend;

import org.sid.ebanckingbackend.entities.AccountOperation;
import org.sid.ebanckingbackend.entities.CurrentAccount;
import org.sid.ebanckingbackend.entities.Customer;
import org.sid.ebanckingbackend.entities.SavingAccount;
import org.sid.ebanckingbackend.enums.AccountStatus;
import org.sid.ebanckingbackend.enums.OperationType;
import org.sid.ebanckingbackend.repositories.AccountOperationRepository;
import org.sid.ebanckingbackend.repositories.BankAccountRepository;
import org.sid.ebanckingbackend.repositories.CustormerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class EbanckingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbanckingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustormerRepository custormerRepository,
                            AccountOperationRepository accountOperationRepository,
                            BankAccountRepository bankAccountRepository
    ){
        return args -> {
            Stream.of("Hassan","Yassine","Salah").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                custormerRepository.save(customer);


            });
            custormerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount=new CurrentAccount();
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreateAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);


                SavingAccount savingAccount=new SavingAccount();
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreateAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setIntersetRate(5.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(
                    acc->{
                        for(int i=0;i<10;i++)
                        {
                            AccountOperation accountOperation=new AccountOperation();
                            accountOperation.setOperationDate(new Date());
                            accountOperation.setAmount(Math.random()*12000);
                            accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
                            accountOperation.setBankAccount(acc);
                            accountOperationRepository.save(accountOperation);
                        }
                    }
            );



        };
    }
}
