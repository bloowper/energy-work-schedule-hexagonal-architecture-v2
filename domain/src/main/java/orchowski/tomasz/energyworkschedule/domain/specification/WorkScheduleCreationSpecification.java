package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;

import java.util.SortedSet;

@NoArgsConstructor(staticName = "getInstance")
public class WorkScheduleCreationSpecification extends AbstractSpecification<SortedSet<Policy>> {

    @Override
    public boolean isSatisfiedBy(SortedSet<Policy> policies) {
        return policies != null && policies.size() > 0;
    }

    @Override
    public void check(SortedSet<Policy> policies) throws GenericSpecificationException {
        if (!isSatisfiedBy(policies)) {
            throw new GenericSpecificationException("At least one policy is required to create WorkSchedule");
        }
    }
}
