package lhc.group.lhc.dto;

import lhc.group.lhc.entity.Collateral;
import lhc.group.lhc.entity.Customer;
import lhc.group.lhc.entity.Loan;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RegistrationDto {
    private CustomerDto customer;
    private LoanDto loan;
    private List<CollateralDto> collaterals;

    public Loan getLoan() {
        return this.loan.toLoanEntity();
    }

    public Customer getCustomer() {
        return this.customer.toCustomerEntity();
    }

    public List<Collateral> getCollaterals() {
        return this.collaterals
                .stream()
                .map(collateralDto -> collateralDto.toCollateralEntity())
                .collect(Collectors.toList());
    }
}
