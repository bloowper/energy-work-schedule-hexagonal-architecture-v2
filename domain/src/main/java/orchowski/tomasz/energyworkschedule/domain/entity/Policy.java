package orchowski.tomasz.energyworkschedule.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Policy {
    @EqualsAndHashCode.Include
    private Id id;
    private TimePeriod effectiveDate;
    private Priority priority;
    private MaxPowerUsageRule maxPowerUsageRule;

    public Policy(Id id, TimePeriod effectiveDate, Priority priority, MaxPowerUsageRule maxPowerUsageRule) {
        this.id = id;
        this.effectiveDate = effectiveDate;
        this.priority = priority;
        this.maxPowerUsageRule = maxPowerUsageRule;
    }

    public Policy(TimePeriod effectiveDate, Priority priority, MaxPowerUsageRule maxPowerUsageRule) {
        this.id = Id.generateNew();
        this.effectiveDate = effectiveDate;
        this.priority = priority;
        this.maxPowerUsageRule = maxPowerUsageRule;
    }
}
