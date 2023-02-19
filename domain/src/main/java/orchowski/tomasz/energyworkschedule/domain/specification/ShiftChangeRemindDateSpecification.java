package orchowski.tomasz.energyworkschedule.domain.specification;

import lombok.NoArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.specification.shared.AbstractSpecification;

import java.time.Instant;
import java.util.Optional;

@NoArgsConstructor(staticName = "getInstance")
public class ShiftChangeRemindDateSpecification extends AbstractSpecification<ShiftChangeRemind> {
    // TODO provide better name of this specification
    @Override
    public boolean isSatisfiedBy(ShiftChangeRemind shiftChangeRemind) {
        Optional<ShiftChangeRemind.ShiftEnd> optionalShiftEnd = shiftChangeRemind.getShiftEnd();
        Optional<ShiftChangeRemind.ShiftStart> optionalShiftStart = shiftChangeRemind.getShiftStart();
        if (optionalShiftStart.isEmpty() || optionalShiftEnd.isEmpty()) {
            // this specification check only relation of instants in end and start
            return true;
        }
        Instant end = optionalShiftEnd.get().getWorkShift().getDuration().getEnd();
        Instant start = optionalShiftStart.get().getWorkShift().getDuration().getStart();
        return end.equals(start);
    }

    @Override
    public void check(ShiftChangeRemind shiftChangeRemind) throws GenericSpecificationException {
        if (!isSatisfiedBy(shiftChangeRemind)) {
            // TODO provide better domain exception message
            throw new GenericSpecificationException("Remind of shift change can only contain following shifts, end of previous must be start of next");
        }
    }
}
