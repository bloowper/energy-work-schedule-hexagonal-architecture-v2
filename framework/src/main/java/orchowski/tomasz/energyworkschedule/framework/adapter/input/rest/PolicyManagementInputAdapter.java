package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.usecase.PolicyManagementUseCase;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.request.AddPowerUsagePolicyRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/device")
class PolicyManagementInputAdapter {
    private final PolicyManagementUseCase policyManagementUseCase;

    @PostMapping("/{device-uuid}/policy")
    Policy createPolicy(@PathVariable("device-uuid") String deviceUuid, @RequestBody AddPowerUsagePolicyRequest createPolicyCommand) {
        Policy powerUsagePolicy = policyManagementUseCase.createPowerUsagePolicy(
                new TimePeriod(createPolicyCommand.getStart(), createPolicyCommand.getEnd()),
                new Priority(createPolicyCommand.getPriority()),
                createPolicyCommand.getMaxAllowedPowerUsage()
        );
        policyManagementUseCase.addPolicyToDevice(Id.withId(deviceUuid), powerUsagePolicy);
        return powerUsagePolicy;
    }

    @GetMapping("/{device-uuid}/policy/{policy-uuid}")
    Optional<Policy> fetchPolicy(@PathVariable("device-uuid") String deviceUuid, @PathVariable("policy-uuid") String policyUuid) {
        return policyManagementUseCase.getPolicy(Id.withId(deviceUuid), Id.withId(policyUuid));
    }

    @DeleteMapping("/{device-uuid}/policy/{policy-uuid}")
    void deletePolicy(@PathVariable("device-uuid") String deviceUuid, @PathVariable("policy-uuid") String policyUuid) {
        policyManagementUseCase.removePolicyFromDevice(Id.withId(deviceUuid), Id.withId(policyUuid));
    }
}
