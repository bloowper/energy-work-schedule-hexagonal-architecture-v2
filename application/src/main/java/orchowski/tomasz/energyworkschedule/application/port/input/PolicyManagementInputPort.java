package orchowski.tomasz.energyworkschedule.application.port.input;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.application.port.output.TaskSchedulerOutputPort;
import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.application.usecase.PolicyManagementUseCase;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

@RequiredArgsConstructor
public class PolicyManagementInputPort implements PolicyManagementUseCase {

    private final DeviceManagementOutputPort deviceManagementOutputPort;
    private final WorkScheduleSnapshotOutputPort workScheduleSnapshotOutputPort;
    private final TaskSchedulerOutputPort taskSchedulerOutputPort;


    @Override
    public Device fetchDevice(Id id) {
        return deviceManagementOutputPort.fetchDevice(id);
    }

    @Override
    public Policy createPowerUsagePolicy(TimePeriod effectiveDate, Priority priority, Double maxAllowedPowerUsage) {
        return new Policy(effectiveDate, priority, new MaxPowerUsageRule(maxAllowedPowerUsage));
    }

    @Override
    public Device addPolicyToDevice(Device device, Policy policy) {
        device.addNewPolicy(policy);
        deviceManagementOutputPort.persistDevice(device);
        taskSchedulerOutputPort.cancelScheduledTasksForDevice(device.getId());
        WorkSchedule newWorkSchedule = persistSnapshotWithWorkSchedule(device);
        newWorkSchedule.getWorkShifts().stream()
                .forEach(workShift -> {
                    taskSchedulerOutputPort.scheduleTask(); //TODO implement this
                });
        return device;
    }

    @Override
    public Device removePolicyFromDevice(Id policyId, Device device) {
        device.removePolicy(policyId);
        deviceManagementOutputPort.persistDevice(device);
        taskSchedulerOutputPort.cancelScheduledTasksForDevice(device.getId());
        WorkSchedule newWorkSchedule = persistSnapshotWithWorkSchedule(device);
        newWorkSchedule.getWorkShifts().stream()
                .forEach(workShift -> {
                    taskSchedulerOutputPort.scheduleTask(); //TODO implement this
                });
        return device;
    }

    private WorkSchedule persistSnapshotWithWorkSchedule(Device device) {
        WorkSchedule workSchedule = device.generateWorkSchedule();
        workScheduleSnapshotOutputPort.persistWorkScheduleForDevice(workSchedule, device.getId());
        return workSchedule;
    }
}
