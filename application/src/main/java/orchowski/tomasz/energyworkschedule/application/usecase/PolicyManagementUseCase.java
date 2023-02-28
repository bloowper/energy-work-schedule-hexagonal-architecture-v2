package orchowski.tomasz.energyworkschedule.application.usecase;

import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;

import java.util.List;
import java.util.Optional;

public interface PolicyManagementUseCase {

    Policy createPowerUsagePolicy(TimePeriod effectiveDate, Priority priority, Double maxAllowedPowerUsage);

    Device addPolicyToDevice(Device device, Policy policy);

    Device addPolicyToDevice(Id deviceId, Policy policy);

    List<Policy> getDevicePolicies(Id deviceId);

    Optional<Policy> getPolicy(Id deviceId, Id policyId);

    Optional<Policy> removePolicyFromDevice(Device device, Id policyId);

    Optional<Policy> removePolicyFromDevice(Id deviceId, Id policyId);

}
