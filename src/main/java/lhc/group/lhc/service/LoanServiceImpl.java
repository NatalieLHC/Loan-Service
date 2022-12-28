package lhc.group.lhc.service;

import lhc.group.lhc.dto.LoanSearchParams;
import lhc.group.lhc.entity.Collateral;
import lhc.group.lhc.entity.Loan;
import lhc.group.lhc.entity.Loan_;
import lhc.group.lhc.repository.CollateralRepository;
import lhc.group.lhc.repository.LoanRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;

import java.util.List;

import static java.time.LocalTime.now;
import static lhc.group.lhc.entity.Loan_.*;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final CustomerService customerService;
    private final CollateralService collateralService;
    private final CollateralRepository collateralRepository;

    public LoanServiceImpl(LoanRepository loanRepository, CustomerService customerService, CollateralService collateralService,
                           CollateralRepository collateralRepository) {
        this.loanRepository = loanRepository;
        this.customerService = customerService;
        this.collateralService = collateralService;
        this.collateralRepository = collateralRepository;
    }

    public Page<Loan> getLoans(LoanSearchParams loanSearchParams, Pageable pageable) {
        return loanRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (loanSearchParams.getLoanId() != null) {
                predicate = cb.and(predicate, cb.equal(root.get(Loan_.LOAN_ID), loanSearchParams.getLoanId()));
            }
            if (StringUtils.isNotEmpty(loanSearchParams.getLoanNumber())) {
                predicate = cb.and(predicate, cb.like(root.get(Loan_.LOAN_NUMBER), '%' + loanSearchParams.getLoanNumber() + '%'));
            }
            if (loanSearchParams.getAmount() != null) {
                predicate = cb.and(predicate, cb.equal(root.get(Loan_.AMOUNT), loanSearchParams.getAmount()));
            }
            if (loanSearchParams.getCreateDateFrom() != null) {
                var createDateFrom = loanSearchParams.getCreateDateFrom().atStartOfDay();
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(Loan_.CREATE_DATE), createDateFrom));
            }
            if (loanSearchParams.getCreateDateTo() != null) {
                var createDateTo = loanSearchParams.getCreateDateTo().atTime(23, 59, 59);
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(Loan_.CREATE_DATE), createDateTo));
            }
//            if (StringUtils.isNotEmpty(loanSearchParams.getUsername())){
//                Join<Loan, Customer> user = root.join(Post_.USER, JoinType.LEFT);
//                predicate = cb.and(predicate,cb.like(user.get(User_.USERNAME), loanSearchParams.getUsername()+ '%'));
//            }
            return predicate;
        }, pageable);
    }

    @Override

    public Loan registerLoan(Loan loan) {
        loan.setLoanId(null);
        if (loan.getCustomer().getCustomerId() == null) {
            customerService.addCustomer(loan.getCustomer());
        }
        if (loan.getCollateral().get( loan.getCollateralId()) == null){
            collateralService.addCollateral(loan.getCollateral());
        }
            return loanRepository.save(loan);
    }
//    @Scheduled(fixedRate = 10000)
//    public Loan updateInterest(){
//        var daily_interest_rate = interestRate / 365;
//        ((amount * daily_interest_rate) / (24 * 60)) * (now() - updateDate);
//
//        return interest+=+daily_interest_rate;
//    }
}


