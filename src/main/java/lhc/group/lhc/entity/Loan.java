package lhc.group.lhc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

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
    private Double amount;
    @Column (name = "interest_rate", nullable = false)
    private Double interestRate;
    private Integer term;
    private Double interest;
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime CreateDate;
    @Column(name = "updated_at", nullable = false,updatable = false, insertable = false)
    private LocalDateTime updateDate;
    @Column(name = "collateral_id", nullable = false,  updatable = false)
    private Integer collateralId;
    @Column(name = "customer_id", nullable = false,  updatable = false, insertable = false)
    private Integer customerId;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "loan")
    private List<Collateral> collateral;
}
