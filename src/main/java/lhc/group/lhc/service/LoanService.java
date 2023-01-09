package lhc.group.lhc.service;

import lhc.group.lhc.dto.LoanSearchParams;
import lhc.group.lhc.dto.RegistrationDto;
import lhc.group.lhc.entity.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface LoanService {
//    Page<Loan> getLoans(LoanSearchParams loanSearchParams, Pageable pageable);

    Loan registerLoan(RegistrationDto registrationDto);
}
