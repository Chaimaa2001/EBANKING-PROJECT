package org.sid.ebanckingbackend.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebanckingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name="TYPE",length = 4)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private double balance;
    private Date createAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
   @ManyToOne
    private Customer customer;
   @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperations;

}
