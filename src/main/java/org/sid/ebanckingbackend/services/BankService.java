package org.sid.ebanckingbackend.services;

import org.sid.ebanckingbackend.entities.BankAccount;
import org.sid.ebanckingbackend.entities.CurrentAccount;
import org.sid.ebanckingbackend.entities.SavingAccount;
import org.sid.ebanckingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
     @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount =
                bankAccountRepository.findById("34e068fc-22b1-4f94-835c-9ab8579cc4ef").orElse(null);

        if (bankAccount != null) {
            System.out.println("------------------------------------");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreateAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("OVER DRAFT:"
                        + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("RATE:" + ((SavingAccount) bankAccount).getIntersetRate());
            }
            bankAccount.getAccountOperations().forEach(
                    op -> {
                        System.out.println(op.getType() + "\t" + op.getAmount() + "\t" + op.getOperationDate());
                    });


        }
    }
}
