package lhc.group.lhc.repository;

import lhc.group.lhc.entity.Collateral;
import lhc.group.lhc.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CollateralRepository extends JpaRepository<Collateral,Integer>, JpaSpecificationExecutor<Collateral> {
}
