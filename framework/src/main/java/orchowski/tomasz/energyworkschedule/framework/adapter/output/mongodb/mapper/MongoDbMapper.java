package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.mapper;

import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.DeviceData;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.MaxPowerUsageRuleData;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.PolicyData;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.WorkScheduleSnapshotData;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.WorkShiftData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MongoDbMapper {
    // [Q] how packages should look when i want separate them but also keep them private inside output.mongodb?? I should use also JPMS????
    Device toDomain(DeviceData deviceData) {
        Device device = new Device(Id.withId(deviceData.getId().toString()));
        deviceData.getPoliciesData().stream()
                .map(this::toDomain)
                .forEach(device::addNewPolicy);
        return device;
    }

    Policy toDomain(PolicyData policyData) {
        return new Policy(
                toDomain(policyData.getId()),
                new TimePeriod(policyData.getStart(), policyData.getStop()),
                new Priority(policyData.getPriority()),
                new MaxPowerUsageRule(policyData.getRule().getValue())
        );
    }

    Id toDomain(UUID uuid) {
        return Id.withId(uuid.toString());
    }

    WorkSchedule toDomain(WorkScheduleSnapshotData workScheduleSnapshotData) {
        return new WorkSchedule(
                new TimePeriod(workScheduleSnapshotData.getStart(), workScheduleSnapshotData.getEnd()),
                workScheduleSnapshotData.getWorkShifts().stream()
                        .map(this::toDomain)
                        .sorted(Comparator.comparing(workShift -> workShift.getDuration().getStart())) // [Q] m8by i should read in in same order as it is persisted?
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    WorkShift toDomain(WorkShiftData workShiftData) {
        return new WorkShift(
                new TimePeriod(workShiftData.getStart(), workShiftData.getEnd()),
                new MaxPowerUsageRule(workShiftData.getRule().getValue())
        );
    }

    DeviceData toData(Device device) {
        List<PolicyData> policiesData = device.getPolicies().stream()
                .map(this::toData)
                .toList();
        return new DeviceData(
                toData(device.getId()),
                policiesData
        );
    }

    PolicyData toData(Policy policy) {
        return new PolicyData(
                toData(policy.getId()),
                policy.getEffectiveDate().getStart(),
                policy.getEffectiveDate().getEnd(),
                policy.getPriority().getValue(),
                new MaxPowerUsageRuleData(policy.getMaxPowerUsageRule().getValue())
        );
    }

    UUID toData(Id id) {
        return id.getUuid();
    }


    WorkScheduleSnapshotData toData(Id deviceId, WorkSchedule workSchedule) {
        List<WorkShiftData> workShifts = workSchedule.getWorkShifts().stream()
                .map(this::toData)
                .toList();
        return new WorkScheduleSnapshotData(
                deviceId.getUuid(),
                workSchedule.getDuration().getStart(),
                workSchedule.getDuration().getEnd(),
                workShifts
        );
    }

    WorkShiftData toData(WorkShift workShift) {
        return new WorkShiftData(
                workShift.getDuration().getStart(),
                workShift.getDuration().getEnd(),
                new MaxPowerUsageRuleData(workShift.getRule().value)
        );
    }

}
