package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;

import java.util.Optional;

@NoArgsConstructor(staticName = "getInstance")
public class ShiftChangeRemindContainingSpecification extends AbstractSpecification<ShiftChangeRemind> {
    // TODO find better name for specification

    @Override
    public boolean isSatisfiedBy(ShiftChangeRemind shiftChangeRemind) {
        Optional<ShiftChangeRemind.ShiftStart> shiftStart = shiftChangeRemind.getShiftStart();
        Optional<ShiftChangeRemind.ShiftEnd> shiftEnd = shiftChangeRemind.getShiftEnd();
        return (shiftStart.isPresent() || shiftEnd.isPresent()) && shiftChangeRemind.getDeviceId() != null;
    }

    @Override
    public void check(ShiftChangeRemind shiftChangeRemind) throws GenericSpecificationException {
        if (!isSatisfiedBy(shiftChangeRemind)) {
            // TODO provide better domain exception message
            throw new GenericSpecificationException("Remind of work shift change must include reference to at least one work shift that starts or ends. Also remind must contain deviceId");
        }
    }
}
