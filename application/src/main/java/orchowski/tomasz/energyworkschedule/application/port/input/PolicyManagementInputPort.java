package orchowski.tomasz.energyworkschedule.application.port.input;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.exception.EntityNotFoundException;
import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.application.usecase.PolicyManagementUseCase;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PolicyManagementInputPort implements PolicyManagementUseCase {
    private final DeviceManagementOutputPort deviceManagementOutputPort;
    private final WorkScheduleSnapshotOutputPort workScheduleSnapshotOutputPort;


    @Override
    public Policy createPowerUsagePolicy(TimePeriod effectiveDate, Priority priority, Double maxAllowedPowerUsage) {
        return new Policy(effectiveDate, priority, new MaxPowerUsageRule(maxAllowedPowerUsage));
    }

    @Override
    public Device addPolicyToDevice(Device device, Policy policy) {
        // [Q] how to handle this better? API user could add policy without calling UseCase method, this lead to not creating snapshot of work-schedule
        device.addNewPolicy(policy);
        deviceManagementOutputPort.persistDevice(device);
        creteSnapshotWithWorkSchedule(device);
        return device;
    }

    @Override
    public Device addPolicyToDevice(Id deviceId, Policy policy) {
        // TODO add tests
        Device device = deviceManagementOutputPort.fetchDevice(deviceId).orElseThrow(() -> new EntityNotFoundException(Device.class, deviceId));
        return addPolicyToDevice(device, policy);
    }

    @Override
    public Optional<Policy> getPolicy(Id deviceId, Id policyId) {
        return deviceManagementOutputPort.fetchDevice(deviceId)
                .flatMap(
                        device -> device.getPolicies().stream()
                                .filter(policy -> policy.getId().equals(policyId))
                                .findFirst()
                );
    }

    @Override
    public Optional<Policy> removePolicyFromDevice(Device device, Id policyId) {
        Optional<Policy> removedPolicy = device.removePolicy(policyId);
        deviceManagementOutputPort.persistDevice(device);
        creteSnapshotWithWorkSchedule(device);
        return removedPolicy;
    }

    @Override
    public Optional<Policy> removePolicyFromDevice(Id deviceId, Id policyId) {
        // TODO add tests
        return deviceManagementOutputPort.fetchDevice(deviceId)
                .flatMap(device -> removePolicyFromDevice(device, policyId));
    }

    private void creteSnapshotWithWorkSchedule(Device device) {
        // [Q] should move this logic to framework hexagon?
        if (!device.getPolicies().isEmpty()) {
            WorkSchedule workSchedule = device.generateWorkSchedule();
            workScheduleSnapshotOutputPort.persistWorkScheduleSnapshot(workSchedule, device.getId());
        } else {
            workScheduleSnapshotOutputPort.removeForDevice(device.getId());
        }
    }
}
