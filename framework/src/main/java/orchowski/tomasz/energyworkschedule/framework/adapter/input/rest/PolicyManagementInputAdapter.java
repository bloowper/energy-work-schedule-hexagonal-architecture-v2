package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.usecase.PolicyManagementUseCase;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class PolicyManagementInputAdapter {
    private final PolicyManagementUseCase policyManagementUseCase;

}
