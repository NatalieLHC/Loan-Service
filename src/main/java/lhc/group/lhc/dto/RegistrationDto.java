package lhc.group.lhc.dto;

import lhc.group.lhc.entity.Collateral;
import lhc.group.lhc.entity.Collateral.CollateralType;
import lhc.group.lhc.entity.Customer;
import lhc.group.lhc.entity.Loan;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class RegistrationDto {

    private Customer customer;
    private Loan loan;
    private List<Collateral>collaterals;

    @Getter
    @Setter
    public static class Loan {
        private String loanNumber;
        private Double amount;
        private Integer term;
        private Double interestRate;

    }

    @Getter
    @Setter
    public static class Customer {
        private  Integer id;
        private String privateNumber;
        private String  firstName;
        private String lastName;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        private LocalDate birthDate;
    }

    @Getter
    @Setter
    public static class Collateral {
        private CollateralType type;
        private Double value;
    }

}
