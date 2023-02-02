package orchowski.tomasz.framework.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.usecase.PolicyManagementUseCase;

@RequiredArgsConstructor
class PolicyManagementInputAdapter {
    private final PolicyManagementUseCase policyManagementUseCase;

}
