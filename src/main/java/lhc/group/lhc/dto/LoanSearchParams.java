package lhc.group.lhc.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LoanSearchParams {
    private Integer LoanId;
    private String LoanNumber;
    private Double amount;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate CreateDateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate CreateDateTo;
    private String username;
    private String collateralType;
    private String collateralValue;

}
