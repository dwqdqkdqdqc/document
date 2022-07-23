package ru.sitronics.tn.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sitronics.tn.document.model.PidEntity;
import ru.sitronics.tn.document.model.ProductionPhase;
import ru.sitronics.tn.document.model.tableEntity.MtrProductionAndShipmentPlanTableEntity;
import ru.sitronics.tn.document.repository.PidEntityRepository;
import ru.sitronics.tn.document.repository.ProductionPhaseRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class PidEntityService {
    private final PidEntityRepository pidEntityRepo;
    private final ProductionPhaseRepository productionPhaseRepo;

    public List<PidEntity> createList(MtrProductionAndShipmentPlanTableEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Was given nul MtrProductionAndShipmentPlanTableEntity");
        }
        if (entity.getQuantity() == null || entity.getQuantity() < 1) {
            throw new IllegalArgumentException("Quantity can't be less then 1");
        }
        List<PidEntity> pidEntities = new ArrayList<>();
        for (int i = 0; i < entity.getQuantity().intValue(); i++) {
            pidEntities.add(create(entity));
        }
        return pidEntities;
    }

    public PidEntity create(MtrProductionAndShipmentPlanTableEntity entity) {
        PidEntity pidEntity = new PidEntity();
        pidEntity.setMtrProductionAndShipmentPlanTableEntity(entity);
        pidEntity.setProductionPhases(createProductionPhasesList(pidEntity, entity.getMtrGroup()));
        return pidEntityRepo.save(pidEntity);
    }

    private List<ProductionPhase> createProductionPhasesList(PidEntity pidEntity, String mtrGroup) {
        List<String> phaseNames = productionPhaseRepo.getPhaseNamesByMtrGroup(mtrGroup);
        if (phaseNames.isEmpty()) {
            throw new EntityNotFoundException("Can't found any phase names for mtrGroup: " + mtrGroup);
        }
        List<ProductionPhase> phaseList = new ArrayList<>();
        AtomicInteger orderNum = new AtomicInteger(1);
        phaseNames.forEach(phaseName -> phaseList.add(productionPhaseRepo
                .save(new ProductionPhase(orderNum.getAndIncrement(), phaseName, pidEntity))));
        return phaseList;
    }
}
