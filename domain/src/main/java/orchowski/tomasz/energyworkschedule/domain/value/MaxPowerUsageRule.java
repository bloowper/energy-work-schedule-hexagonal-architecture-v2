package orchowski.tomasz.energyworkschedule.domain.value;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import orchowski.tomasz.energyworkschedule.domain.specification.MaxPowerUsageRuleSpecification;
import orchowski.tomasz.energyworkschedule.domain.value.shared.Rule;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MaxPowerUsageRule extends Rule<Double> {

    public MaxPowerUsageRule(Double maxAllowedPowerUsage) {
        super(maxAllowedPowerUsage);
        MaxPowerUsageRuleSpecification.getInstance().check(this);
    }

    @Override
    public String getDescription() {
        return "MAX_ALLOWED_POWER_USAGE";
    }
}
