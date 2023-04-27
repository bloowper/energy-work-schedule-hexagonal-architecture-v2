package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;

@NoArgsConstructor(staticName = "getInstance")
public class ShiftEndRemindSpecification extends AbstractSpecification<ShiftChangeRemind.ShiftEndRemind> {

    @Override
    public boolean isSatisfiedBy(ShiftChangeRemind.ShiftEndRemind shiftEndRemind) {
        return shiftEndRemind.getShiftThatEnds() != null && shiftEndRemind.getDeviceId() != null;
    }

    @Override
    public void check(ShiftChangeRemind.ShiftEndRemind shiftEndRemind) throws GenericSpecificationException {
        if (!isSatisfiedBy(shiftEndRemind)) {
            throw new GenericSpecificationException("ShiftEndRemind must contain workShift and deviceId");
        }
    }
}
