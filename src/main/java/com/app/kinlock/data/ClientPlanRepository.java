package com.app.kinlock.data;

import com.app.kinlock.domain.entity.ClientPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientPlanRepository extends GenericRepository<ClientPlan, Integer> {

    List<ClientPlan> findAllBySoldConfirmationIsFalse();
    List<ClientPlan> findAllBySoldConfirmationIsTrue();

}
