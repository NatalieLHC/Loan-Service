package lhc.group.lhc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lhc.group.lhc.dto.RegistrationDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "loansIdGenerator", sequenceName = "loans_id_seq", allocationSize = 1)
@Table(name = "loans")
public class Loan {

    public Loan (RegistrationDto.Loan dto){
        if (dto ==null){
            throw new RuntimeException("loan is null");
        }
        this.loanNumber = dto.getLoanNumber();
        this.amount = dto.getAmount();
        this.interestRate = dto.getInterestRate();
        this.term = dto.getTerm();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loansIdGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column (name = "loan_number", nullable = false)
    private String loanNumber;
    private Double amount;
    @Column (name = "interest_rate", nullable = false)
    private Double interestRate;
    private Integer term;
    private Double interest;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "loan")
    private List<Collateral> collateral;


    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
