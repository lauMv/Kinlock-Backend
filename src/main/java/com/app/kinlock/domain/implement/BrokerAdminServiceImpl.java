package com.app.kinlock.domain.implement;

import com.app.kinlock.common.enums.RoleEnum;
import com.app.kinlock.common.function.FunctionManager;
import com.app.kinlock.common.security.AuthenticationFacade;
import com.app.kinlock.data.BrokerRepository;
import com.app.kinlock.domain.entity.Broker;
import com.app.kinlock.domain.entity.Plan;
import com.app.kinlock.domain.service.BrokerAdminService;
import com.app.kinlock.exceptions.DuplicatedException;
import com.app.kinlock.presentation.dto.BrokerDto;
import com.app.kinlock.presentation.pojo.BrokerPojo;
import com.app.kinlock.presentation.pojo.PlanPojo;
import com.app.kinlock.utils.FunctionNames;
import lombok.AllArgsConstructor;
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
    private final FunctionManager<Integer> integerFunctionManager;

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
            PlanPojo pojo = executePlanPojo(plan.getId());
            pojos.add(pojo);
        }
        return pojos;
    }

    private PlanPojo executePlanPojo(Integer id) {
        return (PlanPojo) integerFunctionManager.executeAndReturn(FunctionNames.GET_PLAN_POJO, id);
    }
}
