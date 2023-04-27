package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;

@NoArgsConstructor(staticName = "getInstance")
public class ShiftStartRemindSpecification extends AbstractSpecification<ShiftChangeRemind.ShiftStartRemind> {

    @Override
    public boolean isSatisfiedBy(ShiftChangeRemind.ShiftStartRemind shiftStartRemind) {
        return shiftStartRemind.getShiftThatStarts() != null && shiftStartRemind.getDeviceId() != null;
    }

    @Override
    public void check(ShiftChangeRemind.ShiftStartRemind shiftStartRemind) throws GenericSpecificationException {
        if (!isSatisfiedBy(shiftStartRemind)) {
            throw new GenericSpecificationException("ShiftStartRemind must contain workShift and deviceId");
        }
    }
}
