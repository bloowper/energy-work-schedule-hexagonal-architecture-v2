package orchowski.tomasz.energyworkschedule.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import orchowski.tomasz.energyworkschedule.domain.specification.NewPolicySpecification;
import orchowski.tomasz.energyworkschedule.domain.specification.WorkScheduleCreationSpecification;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Device {
    @EqualsAndHashCode.Include
    private Id id;
    private SortedSet<Policy> policies;

    public Device(Id id) {
        this.id = id;
        this.policies = new TreeSet<>(Comparator.comparing(o -> o.getEffectiveDate().getStart()));
    }

    public void addNewPolicy(Policy policy) {
        NewPolicySpecification.basedOnOldPolicies(policies).check(policy);
        policies.add(policy);
    }

    public void removePolicy(Id policyId) {
        policies.removeIf(policy -> policy.getId().equals(policyId));
    }

    public WorkSchedule generateWorkSchedule() {
        WorkScheduleCreationSpecification.getInstance().check(policies);
        return WorkScheduleCreator.of(policies).createWorkSchedule();
    }

}
