package lhc.group.lhc.service;


import lhc.group.lhc.entity.Collateral;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollateralService {
    Collateral addCollateral(Collateral collateral);

}
