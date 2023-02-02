package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;

@NoArgsConstructor(staticName = "getInstance")
public class PrioritySpecification extends AbstractSpecification<Priority> {
    private static final Integer MIN_ALLOWED_PRIORITY = 0;

    @Override
    public boolean isSatisfiedBy(Priority priority) {
        return priority.getValue() >= MIN_ALLOWED_PRIORITY;
    }

    @Override
    public void check(Priority priority) throws GenericSpecificationException {
        if (!isSatisfiedBy(priority)) {
            throw new GenericSpecificationException("Priority is below %s".formatted(MIN_ALLOWED_PRIORITY));
        }
    }
}
