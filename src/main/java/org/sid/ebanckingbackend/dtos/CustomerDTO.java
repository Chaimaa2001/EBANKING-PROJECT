package org.sid.ebanckingbackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.ebanckingbackend.entities.BankAccount;

import java.util.List;


@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;

}
