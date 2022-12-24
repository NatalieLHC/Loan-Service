package lhc.group.lhc.controller;

import lhc.group.lhc.dto.LoanSearchParams;
import lhc.group.lhc.entity.Loan;
import lhc.group.lhc.repository.LoanRepository;
import lhc.group.lhc.service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanRepository loanRepository;
    private final LoanService loanService;

    public LoanController(LoanRepository loanRepository, LoanService loanService) {
        this.loanRepository = loanRepository;
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getLoans(LoanSearchParams loanSearchParams){
        return loanService.getLoans(loanSearchParams);
    }

}
