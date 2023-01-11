package lhc.group.lhc.service;

import lhc.group.lhc.dto.LoanSearchParams;
import lhc.group.lhc.dto.RegistrationDto;
import lhc.group.lhc.entity.Collateral;
import lhc.group.lhc.entity.Customer;
import lhc.group.lhc.entity.Loan;
//import lhc.group.lhc.entity.Loan_;
import lhc.group.lhc.entity.Loan_;
import lhc.group.lhc.repository.CollateralRepository;
import lhc.group.lhc.repository.CustomerRepository;
import lhc.group.lhc.repository.LoanRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalTime.now;
//import static lhc.group.lhc.entity.Loan_.*;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final CollateralRepository collateralRepository;

    public LoanServiceImpl(LoanRepository loanRepository, CustomerRepository customerRepository,
                           CollateralRepository collateralRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
        this.collateralRepository = collateralRepository;
    }

    public Page<Loan> getLoans(LoanSearchParams loanSearchParams, Pageable pageable) {
        return loanRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (loanSearchParams.getLoanId() != null) {
                predicate = cb.and(predicate, cb.equal(root.get(Loan_.ID), loanSearchParams.getLoanId()));
            }
            if (StringUtils.isNotEmpty(loanSearchParams.getLoanNumber())) {
                predicate = cb.and(predicate, cb.like(root.get(Loan_.LOAN_NUMBER), '%' + loanSearchParams.getLoanNumber() + '%'));
            }
            if (loanSearchParams.getAmount() != null) {
                predicate = cb.and(predicate, cb.equal(root.get(Loan_.AMOUNT), loanSearchParams.getAmount()));
            }
            if (loanSearchParams.getCreateDateFrom() != null) {
                var createDateFrom = loanSearchParams.getCreateDateFrom().atStartOfDay();
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(Loan_.CREATED_AT), createDateFrom));
            }
            if (loanSearchParams.getCreateDateTo() != null) {
                var createDateTo = loanSearchParams.getCreateDateTo().atTime(23, 59, 59);
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(Loan_.UPDATED_AT), createDateTo));
            }
//            if (StringUtils.isNotEmpty(loanSearchParams.getUsername())){
//                Join<Loan, Customer> user = root.join(Post_.USER, JoinType.LEFT);
//                predicate = cb.and(predicate,cb.like(user.get(User_.USERNAME), loanSearchParams.getUsername()+ '%'));
//            }
            return predicate;
        }, pageable);
    }

    @Override
    @Transactional
    public Loan registerLoan(RegistrationDto registrationDto) {

        var customerDto = registrationDto.getCustomer();
        var customer = new Customer(customerDto);
        if (customer.getCustomerId()==null){
            customerRepository.save(customer);
        }

        var loanDto = registrationDto.getLoan();
        var loan = new Loan(loanDto);
        loan.setCustomer(customer);
        loanRepository.save(loan);

        var collateralDtos = registrationDto.getCollaterals();
        collateralDtos.forEach(collateralDto -> {
            var collateral = new Collateral(collateralDto);
            collateral.setLoan(loan);
            collateralRepository.save(collateral);
        });
        return loan;
    }
    public  Loan getById(int id){
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    @Scheduled(fixedRate = 60 *100000)
    public void calculateInterest() {
        loanRepository.findAll().forEach(this::updateInterest);
    }
    public void updateInterest(Loan loan){
        var interestRate = loan.getInterestRate();
        var dailyIntRate = interestRate/365;
        long timeDiff = Math.abs(Duration.between(loan.getCreatedAt(), LocalDateTime.now()).toMinutes());
        var interest = loan.getAmount() * dailyIntRate / (24 * 60) *timeDiff;
        loan.setInterest(interest);
        loanRepository.save(loan);
    }
}


