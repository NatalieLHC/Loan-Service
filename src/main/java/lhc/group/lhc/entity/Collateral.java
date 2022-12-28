package lhc.group.lhc.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "collateralsIdGenerator", sequenceName = "collateral_id_seq", allocationSize = 1)
@Table(name = "collaterals")
public class Collateral {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collateralsIdGenerator")
    @Column(name = "id", nullable = false)
    private Integer collateralId;
    private String type;
    private String value;
    @Column(name = "created_at", nullable = false, updatable = false, insertable = false)
    private LocalDateTime CreateDate;
    @Column(name = "updated_at", updatable = false, insertable = false)
    private LocalDateTime updateDate;
    @Column(name = "loan_id", insertable = false, updatable = false)
    private Integer LoanId;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;

}
