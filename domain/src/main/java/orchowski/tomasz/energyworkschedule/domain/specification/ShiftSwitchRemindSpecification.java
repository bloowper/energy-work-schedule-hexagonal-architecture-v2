package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;

@NoArgsConstructor(staticName = "getInstance")
public class ShiftSwitchRemindSpecification extends AbstractSpecification<ShiftChangeRemind.ShiftSwitchRemind> {
    // TODO provide better name of this specification
    @Override
    public boolean isSatisfiedBy(ShiftChangeRemind.ShiftSwitchRemind shiftChangeRemind) {
        return shiftChangeRemind.getShiftThatEnds() != null &&
                shiftChangeRemind.getShiftThatStarts() != null &&
                shiftChangeRemind.getDeviceId() != null &&
                shiftChangeRemind.getShiftThatEnds().getEnd().equals(shiftChangeRemind.getShiftThatStarts().getStart());
    }

    @Override
    public void check(ShiftChangeRemind.ShiftSwitchRemind shiftChangeRemind) throws GenericSpecificationException {
        if (!isSatisfiedBy(shiftChangeRemind)) {
            // TODO provide better domain exception message
            throw new GenericSpecificationException("Remind of shift change can only contain following shifts, end of previous must be start of next");
        }
    }
}
