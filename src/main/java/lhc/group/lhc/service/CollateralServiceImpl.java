package lhc.group.lhc.service;

import lhc.group.lhc.entity.Collateral;
import lhc.group.lhc.repository.CollateralRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollateralServiceImpl implements CollateralService {

    private final CollateralRepository collateralRepository;


    public CollateralServiceImpl(CollateralRepository collateralRepository) {
        this.collateralRepository=collateralRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Collateral addCollateral(Collateral collateral) {
        collateral.setCollateralId(null);
        return collateralRepository.save(collateral);
    }
}
