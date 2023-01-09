package lhc.group.lhc.dto;

import lhc.group.lhc.entity.Collateral;
import lhc.group.lhc.entity.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollateralDto {
    private Collateral.CollateralType type;
    private  Double value;

    public Collateral toCollateralEntity(){
        return Collateral.builder()
                .type(this.getType())
                .value(this.getValue())
                .build();
    }
}
