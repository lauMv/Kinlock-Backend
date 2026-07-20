package com.app.kinlock.domain.events;

import com.app.kinlock.domain.entity.Plan;

public class PlanCreatedEvent {

    private final Plan plan;
    private final String creatorEmail;
    private final Integer targetBrokerId;

    public PlanCreatedEvent(Plan plan, String creatorEmail) {
        this(plan, creatorEmail, null);
    }

    public PlanCreatedEvent(Plan plan, String creatorEmail, Integer targetBrokerId) {
        this.plan = plan;
        this.creatorEmail = creatorEmail;
        this.targetBrokerId = targetBrokerId;
    }

    public Plan getPlan() {
        return plan;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public Integer getTargetBrokerId() {
        return targetBrokerId;
    }
}