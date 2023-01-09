package lhc.group.lhc.dto;

import lhc.group.lhc.entity.Collateral;
import lhc.group.lhc.entity.Customer;
import lhc.group.lhc.entity.Loan;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class CustomerDto {

    private Integer customerId;
    private String personalNumber;
    private  String firstName;
    private String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime birthDate;

    public Customer toCustomerEntity(){
        return Customer.builder()
                .customerId(this.customerId)
                .personalNumber(this.personalNumber)
                .firstName(this.firstName)
                .build();
    }
}
