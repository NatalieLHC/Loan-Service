package lhc.group.lhc.dto;

import lhc.group.lhc.entity.Loan;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class LoanDto {
    private Integer loanId;
    private String LoanNumber;
    private Double amount;
    private Double interestRate;
    private Integer term;
    private Double interest;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime createDate;

    public Loan toLoanEntity(){
        return Loan.builder()
                .loanId(this.loanId)
                .LoanNumber(this.LoanNumber)
                .amount(this.amount)
                .build();
    }
}
