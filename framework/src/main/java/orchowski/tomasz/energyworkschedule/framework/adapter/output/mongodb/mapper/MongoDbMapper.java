package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.mapper;

import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.DeviceData;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.MaxPowerUsageRuleData;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.PolicyData;

import java.util.List;
import java.util.UUID;

public class MongoDbMapper {
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
}
