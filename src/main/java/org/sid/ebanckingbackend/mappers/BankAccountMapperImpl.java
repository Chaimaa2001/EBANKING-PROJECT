package org.sid.ebanckingbackend.mappers;

import org.sid.ebanckingbackend.dtos.AccountOperationDTO;
import org.sid.ebanckingbackend.dtos.CurrentBankAccountDTO;
import org.sid.ebanckingbackend.dtos.CustomerDTO;
import org.sid.ebanckingbackend.dtos.SavingBankAccountDTO;
import org.sid.ebanckingbackend.entities.AccountOperation;
import org.sid.ebanckingbackend.entities.CurrentAccount;
import org.sid.ebanckingbackend.entities.Customer;
import org.sid.ebanckingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
//MapStruct
@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        //customerDTO.setId(customer.getId());
        //customerDTO.setName(customerDTO.getName());
        //customerDTO.setEmail(customerDTO.getEmail());
        return customerDTO;
    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }
    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount)
    {
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer())); savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }
   public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
       SavingAccount savingAccount=new SavingAccount();
       BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
       return savingAccount;
    }
    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount)
    {
        CurrentBankAccountDTO currentBankAccountDTO=new CurrentBankAccountDTO(); BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));  currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());                                                                       return currentBankAccountDTO ;}
    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO)
    {CurrentAccount currenAccount=new CurrentAccount(); BeanUtils.copyProperties(currentBankAccountDTO,currenAccount);
        currenAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO())); return currenAccount;}
          public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
            AccountOperationDTO  accountOperationDTO    =new AccountOperationDTO();       BeanUtils.copyProperties(accountOperation,accountOperationDTO); return accountOperationDTO;}
    }



