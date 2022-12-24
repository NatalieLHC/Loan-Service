package lhc.group.lhc.service;

import lhc.group.lhc.dto.LoanSearchParams;
import lhc.group.lhc.entity.Loan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoanService {
    List<Loan>getLoans(LoanSearchParams loanSearchParams);

//    Loan registerLoan(Loan loan);
}
