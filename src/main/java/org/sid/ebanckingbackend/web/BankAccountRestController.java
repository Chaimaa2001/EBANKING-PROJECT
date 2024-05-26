package org.sid.ebanckingbackend.web;

import lombok.AllArgsConstructor;
import org.sid.ebanckingbackend.dtos.BankAccountDTO;
import org.sid.ebanckingbackend.dtos.CreditDTO;
import org.sid.ebanckingbackend.dtos.DebitDTO;
import org.sid.ebanckingbackend.dtos.TransferRequestDTO;
import org.sid.ebanckingbackend.exception.BalanceNotSufficentException;
import org.sid.ebanckingbackend.exception.BankAccountNotFoundException;
import org.sid.ebanckingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);

    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }

    @PostMapping("/accounts/debit")
   public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficentException {
        this.bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
   }

    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficentException {
        this.bankAccountService.transfer(transferRequestDTO.getAccountSource()
                ,transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());

    }

}
