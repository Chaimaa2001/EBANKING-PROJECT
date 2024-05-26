package org.sid.ebanckingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.ebanckingbackend.dtos.AccountHistoryDTO;
import org.sid.ebanckingbackend.dtos.AccountOperationDTO;
import org.sid.ebanckingbackend.dtos.CustomerDTO;
import org.sid.ebanckingbackend.exception.BankAccountNotFoundException;
import org.sid.ebanckingbackend.exception.CustomerNotFoundException;
import org.sid.ebanckingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers() {

        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchcustomers(@RequestParam(name="keyword",defaultValue = "") String keyword) {
        return bankAccountService.searchCustomers("%"+keyword+"%");
    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
         return bankAccountService.saveCustomer(customerDTO);

    }
    @PutMapping("customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO)
    {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id)
    {
        bankAccountService.deleteCustomer(id);
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId)
    {
        return bankAccountService.AccountHistory(accountId);
    }
    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name="page",defaultValue = "0") int page,
                                               @RequestParam(name="size",defaultValue = "5")int size) throws BankAccountNotFoundException {
         return bankAccountService.getAccountHistory(accountId,page,size);
    }
}
