package org.sid.ebanckingbackend;

import org.sid.ebanckingbackend.dtos.BankAccountDTO;
import org.sid.ebanckingbackend.dtos.CurrentBankAccountDTO;
import org.sid.ebanckingbackend.dtos.CustomerDTO;
import org.sid.ebanckingbackend.dtos.SavingBankAccountDTO;
import org.sid.ebanckingbackend.entities.*;
import org.sid.ebanckingbackend.enums.AccountStatus;
import org.sid.ebanckingbackend.enums.OperationType;
import org.sid.ebanckingbackend.exception.BalanceNotSufficentException;
import org.sid.ebanckingbackend.exception.BankAccountNotFoundException;
import org.sid.ebanckingbackend.exception.CustomerNotFoundException;
import org.sid.ebanckingbackend.repositories.AccountOperationRepository;
import org.sid.ebanckingbackend.repositories.BankAccountRepository;
import org.sid.ebanckingbackend.repositories.CustormerRepository;
import org.sid.ebanckingbackend.services.BankAccountService;
import org.sid.ebanckingbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;



@SpringBootApplication
public class EbanckingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbanckingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
    return args ->{
        Stream.of("Hassan","Imane","Mohamed").forEach(name->{
            CustomerDTO customer=new CustomerDTO();
            customer.setName(name);
            customer.setEmail(name+"@gmail.com");
            bankAccountService.saveCustomer(customer);
                });
        bankAccountService.listCustomers().forEach(customer -> {
            try {
                bankAccountService.saveCurrentBankAccount(Math.random()*9000,9000, customer.getId());
                bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

            } catch (CustomerNotFoundException e) {
                e.printStackTrace();
            }

        });
        List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
        for(BankAccountDTO bankAccount:bankAccounts)
        {
            for(int i=0;i<10;i++)
            {
                String accountId;
                if(bankAccount instanceof SavingBankAccountDTO)
                {
                    accountId=((SavingBankAccountDTO) bankAccount).getId();
                }else
                {
                    accountId=((CurrentBankAccountDTO)bankAccount).getId();
                }
                bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
            }
        }


    };
}
    //@Bean
    CommandLineRunner start(CustormerRepository custormerRepository,
                            AccountOperationRepository accountOperationRepository,
                            BankAccountRepository bankAccountRepository
    ) {
        return args -> {
            Stream.of("Hassan", "Yassine", "Salah").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                custormerRepository.save(customer);


            });
            custormerRepository.findAll().forEach(cust -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreateAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);


                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreateAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setIntersetRate(5.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(
                    acc -> {
                        for (int i = 0; i < 10; i++) {
                            AccountOperation accountOperation = new AccountOperation();
                            accountOperation.setOperationDate(new Date());
                            accountOperation.setAmount(Math.random() * 12000);
                            accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                            accountOperation.setBankAccount(acc);
                            accountOperationRepository.save(accountOperation);
                        }


                    });

        };
    }
}
