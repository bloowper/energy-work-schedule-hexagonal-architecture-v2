package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;

@NoArgsConstructor(staticName = "getInstance")
public class TimePeriodSpecification extends AbstractSpecification<TimePeriod> {

    @Override
    public boolean isSatisfiedBy(TimePeriod timePeriod) {
        return timePeriod.getStart().isBefore(timePeriod.getEnd());
    }

    @Override
    public void check(TimePeriod timePeriod) throws GenericSpecificationException {
        if (!isSatisfiedBy(timePeriod)) {
            throw new GenericSpecificationException("date %s is not less than date %s".formatted(timePeriod.getStart(), timePeriod.getEnd()));
        }

    }
}
