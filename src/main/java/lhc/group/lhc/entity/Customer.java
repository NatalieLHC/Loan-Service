package lhc.group.lhc.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lhc.group.lhc.dto.RegistrationDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@SequenceGenerator(name = "customersIdGenerator", sequenceName = "customers_id_seq", allocationSize = 1)
@Table(name = "customers")
public class Customer {

    public Customer(RegistrationDto.Customer dto){
        this.customerId = dto.getId();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.privateNumber = dto.getPrivateNumber();
        this.birthDate = dto.getBirthDate();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customersIdGenerator")
    @Column(name = "id", nullable = false)
    private Integer customerId;
    @Column(name = "private_number", nullable = false, unique = true)
    private String privateNumber;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "created_at",  updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Loan> loans;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

}
