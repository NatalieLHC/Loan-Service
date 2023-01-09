package lhc.group.lhc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
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
    private LocalDateTime createDate;
    @Column(name = "updated_at", updatable = false, insertable = false)
    private LocalDateTime updateDate;
    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Collateral> collateral;

    public Loan() {

    }
}
