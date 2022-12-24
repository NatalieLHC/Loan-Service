package lhc.group.lhc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@SequenceGenerator(name = "loansIdGenerator", sequenceName = "loans_id_seq", allocationSize = 1)
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loansIdGenerator")
    @Column(name = "id", nullable = false)
    private Integer loanId;
    @Column (name = "loan_number", nullable = false)
    private String LoanNumber;
    @Column (name = "loan_number", nullable = false)
    private String LoanNumber;
}
