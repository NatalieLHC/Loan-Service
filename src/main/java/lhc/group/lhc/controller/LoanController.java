package lhc.group.lhc.controller;

import lhc.group.lhc.dto.LoanSearchParams;
import lhc.group.lhc.dto.RegistrationDto;
import lhc.group.lhc.entity.Loan;
import lhc.group.lhc.repository.LoanRepository;
import lhc.group.lhc.service.LoanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

//    @GetMapping
//    public Page<Loan> getLoans(LoanSearchParams loanSearchParams,
//                               @RequestParam(required = false, defaultValue = "0") int page,
//                               @RequestParam(required = false, defaultValue = "10") int size) {
//        return loanService.getLoans(loanSearchParams, PageRequest.of(page, size));
//    }

    @PostMapping
    public ResponseEntity<Loan> registerLoan(@RequestBody RegistrationDto regDto) {

        var location = UriComponentsBuilder.fromPath("/loans/" + regDto.getLoan().getLoanId()).build().toUri();
        return ResponseEntity.created(location).body(loanService.registerLoan(regDto));
    }

}
