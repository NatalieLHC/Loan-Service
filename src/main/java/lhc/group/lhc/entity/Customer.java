package lhc.group.lhc.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@SequenceGenerator(name = "customersIdGenerator", sequenceName = "customers_id_seq", allocationSize = 1)
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customersIdGenerator")
    @Column(name = "id", nullable = false)
    private Integer customerId;
    @Column(name = "private_number", nullable = false)
    private String personalNumber;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "created_at",  updatable = false, insertable = false)
    private LocalDateTime createDate;
    @Column(name = "updated_at", nullable = false,  updatable = false, insertable = false)
    private LocalDateTime updateDate;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Loan> loans;
}
