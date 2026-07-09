package com.app.kinlock.domain.events;

import com.app.kinlock.domain.entity.Plan;

public class PlanCreatedEvent {

    private final Plan plan;
    private final String creatorEmail;

    public PlanCreatedEvent(Plan plan, String creatorEmail) {
        this.plan = plan;
        this.creatorEmail = creatorEmail;
    }

    public Plan getPlan() {
        return plan;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }
}
