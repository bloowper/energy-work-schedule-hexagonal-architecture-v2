package orchowski.tomasz.energyworkschedule.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.specification.NewPolicySpecification;
import orchowski.tomasz.energyworkschedule.domain.specification.WorkScheduleCreationSpecification;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public class Device {
    @EqualsAndHashCode.Include
    private final Id id;
    private final SortedSet<Policy> policies = new TreeSet<>(Comparator.comparing(o -> o.getEffectiveDate().getStart()));

    public Device(Id id) {
        this.id = id;
    }

    public void addNewPolicy(Policy policy) {
        NewPolicySpecification.basedOnOldPolicies(policies).check(policy);
        policies.add(policy);
    }

    public Optional<Policy> removePolicy(Id policyId) {
        Predicate<Policy> policyPredicate = policy -> policyId.equals(policy.getId());
        Optional<Policy> optionalPolicy = policies.stream()
                .filter(policyPredicate)
                .findFirst();
        policies.removeIf(policyPredicate);
        return optionalPolicy;
    }

    public WorkSchedule generateWorkSchedule() {
        WorkScheduleCreationSpecification.getInstance().check(policies);
        return WorkScheduleCreator.of(policies).createWorkSchedule();
    }

    public List<ShiftChangeRemind> generateShiftChangeReminds() {
        // [Q] do i need to optimization for generateWorkSchedule in application layer flow?
        return ShiftChangeRemindCreator.basedOn(id, generateWorkSchedule()).createReminders();
    }

}
