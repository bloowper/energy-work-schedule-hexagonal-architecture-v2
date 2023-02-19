package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;

@NoArgsConstructor(staticName = "getInstance")
public class ShiftChangeRemindChangeToSwitchSpecification extends AbstractSpecification<ShiftChangeRemind> {


    @Override
    public boolean isSatisfiedBy(ShiftChangeRemind shiftChangeRemind) {
        return shiftChangeRemind.getType().equals(ShiftChangeRemind.RemindType.END_OF_SHIFT);
    }

    @Override
    public void check(ShiftChangeRemind shiftChangeRemind) throws GenericSpecificationException {
        if (!isSatisfiedBy(shiftChangeRemind)) {
            // TODO provide better domain exception message
            throw new GenericSpecificationException("ShiftChangeRemind required prior and following work shifts to be of type SWITCH_OF_SHIFT");
        }

    }
}
