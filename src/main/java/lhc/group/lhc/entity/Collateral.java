package lhc.group.lhc.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lhc.group.lhc.dto.RegistrationDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "collateralsIdGenerator", sequenceName = "collaterals_id_seq", allocationSize = 1)
@Table(name = "collaterals")
public class Collateral {

    public Collateral(RegistrationDto.Collateral dto){
        this.type = dto.getType();
        this.value = dto.getValue();
    }

    public enum CollateralType {
        CAR,
        APARTMENT,
        LAND
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collateralsIdGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "type")
    private  CollateralType type;
    private Double value;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "loan_id", referencedColumnName = "id")
    private Loan loan;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
