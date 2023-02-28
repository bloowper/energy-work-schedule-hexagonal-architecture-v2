package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;

@NoArgsConstructor(staticName = "getInstance")
public class MaxPowerUsageRuleSpecification extends AbstractSpecification<MaxPowerUsageRule> {
    private static final Double MIN_ALLOWED_POWER = 0.;

    @Override
    public boolean isSatisfiedBy(MaxPowerUsageRule maxPowerUsageRule) {
        return maxPowerUsageRule.getValue() >= MIN_ALLOWED_POWER;
    }

    @Override
    public void check(MaxPowerUsageRule maxPowerUsageRule) throws GenericSpecificationException {
        if (!isSatisfiedBy(maxPowerUsageRule)) {
            throw new GenericSpecificationException("Rule %s cannot have negative value, but was %s".formatted(maxPowerUsageRule.getDescription(), maxPowerUsageRule.getValue()));
        }

    }
}
