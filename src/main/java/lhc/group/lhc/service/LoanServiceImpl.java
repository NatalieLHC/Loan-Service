package lhc.group.lhc.service;

import lhc.group.lhc.dto.LoanSearchParams;
import lhc.group.lhc.entity.Customer;
import lhc.group.lhc.entity.Loan;
import lhc.group.lhc.entity.Loan_;
import lhc.group.lhc.repository.LoanRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    public List<Loan>getLoans(LoanSearchParams loanSearchParams){
        return loanRepository.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (loanSearchParams.getLoanId() !=null){
                predicate = cb.and(predicate, cb.equal(root.get(Loan_.LOAN_ID), loanSearchParams.getLoanId()));
            }
            if (StringUtils.isNotEmpty(loanSearchParams.getLoanNumber())){
                predicate = cb.and(predicate, cb.like(root.get(Loan_.LOAN_NUMBER), '%' + loanSearchParams.getLoanNumber() + '%'));
            }
            if (loanSearchParams.getAmount() !=null){
                predicate = cb.and(predicate, cb.equal(root.get(Loan_.AMOUNT), loanSearchParams.getAmount()));
            }
            if (loanSearchParams.getCreateDateFrom() !=null){
                var createDateFrom = loanSearchParams.getCreateDateFrom().atStartOfDay();
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(Loan_.CREATE_DATE), createDateFrom));
            }
            if (loanSearchParams.getCreateDateTo() !=null){
                var  createDateTo = loanSearchParams.getCreateDateTo().atTime(23, 59, 59);
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(Loan_.CREATE_DATE), createDateTo));
            }
//            if (StringUtils.isNotEmpty(loanSearchParams.getUsername())){
//                Join<Loan, Customer> user = root.join(Post_.USER, JoinType.LEFT);
//                predicate = cb.and(predicate,cb.like(user.get(User_.USERNAME), loanSearchParams.getUsername()+ '%'));
//            }
            return predicate;
        });
    }
}


