package com.app.kinlock.domain.implement;

import com.app.kinlock.common.enums.RoleEnum;
import com.app.kinlock.common.security.AuthenticationFacade;
import com.app.kinlock.data.BrokerRepository;
import com.app.kinlock.domain.entity.Broker;
import com.app.kinlock.domain.entity.ClientPlan;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.events.PlanCreatedEvent;
import com.app.kinlock.domain.service.BrokerAdminService;
import com.app.kinlock.domain.service.ClientPlanService;
import com.app.kinlock.domain.service.PlanService;
import com.app.kinlock.exceptions.DuplicatedException;
import com.app.kinlock.exceptions.EntityNotFoundException;
import com.app.kinlock.presentation.dto.BrokerDto;
import com.app.kinlock.presentation.pojo.BrokerPojo;
import com.app.kinlock.presentation.pojo.PlanPojo;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BrokerAdminServiceImpl implements BrokerAdminService {

    private final BrokerRepository brokerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade auth;
    private final ClientPlanService clientPlanService;
    private final PlanService planService;

    @Override
    public void save(Broker broker) {
        brokerRepository.save(broker);
    }

    @Override
    public Broker createBroker(BrokerDto dto) {
        if (brokerRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicatedException("El correo ya esta en uso");
        }

        Broker b = new Broker();
        b.setName(dto.getName());
        b.setCi(dto.getCi());
        b.setEmail(dto.getEmail());
        b.setPassword(passwordEncoder.encode(dto.getPassword()));
        b.setRole(RoleEnum.ROLE_BROKER);
        return brokerRepository.save(b);
    }

    @Override
    public Broker update(BrokerDto dto) {
        Broker broker = brokerRepository.findByEmail(auth.getEmail());

        broker.setName(dto.getName() != null ? dto.getName() : broker.getName());
        broker.setCi(dto.getCi() != null ? dto.getCi() : broker.getCi());
        broker.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : broker.getPassword());
        broker.setLogo(dto.getLogo());
        broker.setRole(RoleEnum.ROLE_BROKER);
        return brokerRepository.save(broker);
    }

    @Override
    public List<BrokerPojo> getAllBrokersPojo() {
        return brokerRepository.getAllPojo();
    }

    @Override
    public Broker findByEmail(String email) {
        return brokerRepository.findByEmail(email);
    }

    @Override
    public Broker getBrokerInfo() {
        return brokerRepository.findByEmail(auth.getEmail());
    }

    @Override
    public List<PlanPojo> getPlansByBroker() {
        Broker broker = brokerRepository.findByEmail(auth.getEmail());
        List<PlanPojo> pojos = new ArrayList<>();
        for (Plan plan : broker.getPlans()) {
            pojos.add(planService.getPojoById(plan.getId()));
        }
        return pojos;
    }

    @Override
    public void confirmSoldPlan(Integer id) {
        clientPlanService.confirmSoldPlan(id);
    }

    @Override
    public List<PlanPojo> getSoldPlansByBroker() {
        Broker broker = brokerRepository.findByEmail(auth.getEmail());
        List<PlanPojo> pojos = new ArrayList<>();
        for (ClientPlan plan : clientPlanService.getSoldPlans()) {
            boolean belongsToBroker = broker.getPlans().stream()
                    .anyMatch(p -> p.getId().equals(plan.getPlan().getId()));
            if (belongsToBroker) {
                pojos.add(planService.getPojoById(plan.getPlan().getId()));
            }
        }
        return pojos;
    }

    @Override
    public List<PlanPojo> getWaitingListPlansByBroker() {
        Broker broker = brokerRepository.findByEmail(auth.getEmail());
        List<PlanPojo> pojos = new ArrayList<>();
        for (ClientPlan plan : clientPlanService.getOnHoldPlans()) {
            boolean belongsToBroker = broker.getPlans().stream()
                    .anyMatch(p -> p.getId().equals(plan.getPlan().getId()));
            if (belongsToBroker) {
                pojos.add(planService.getPojoById(plan.getPlan().getId()));
            }
        }
        return pojos;
    }

    @EventListener
    public void onPlanCreated(PlanCreatedEvent event) {
        Broker broker;
        if (event.getTargetBrokerId() != null) {
            broker = brokerRepository.findById(event.getTargetBrokerId())
                    .orElseThrow(() -> new EntityNotFoundException("Broker", event.getTargetBrokerId()));
        } else {
            broker = brokerRepository.findByEmail(event.getCreatorEmail());
            if (broker == null) {
                return;
            }
        }
        Plan plan = event.getPlan();
        plan.setBroker(broker);
        planService.save(plan);
    }
}
