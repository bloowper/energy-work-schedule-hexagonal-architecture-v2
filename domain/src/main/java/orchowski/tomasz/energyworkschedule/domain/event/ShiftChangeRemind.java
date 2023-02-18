package orchowski.tomasz.energyworkschedule.domain.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import orchowski.tomasz.energyworkschedule.domain.specification.ShiftChangeRemindContainingSpecification;
import orchowski.tomasz.energyworkschedule.domain.specification.ShiftChangeRemindSwitchTimingSpecification;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;

import java.util.Optional;

@EqualsAndHashCode
@Getter
public class ShiftChangeRemind {
    // [Q] is using optionals af fields is ok? How such design should looks like?
    private final Id deviceId;
    private final Optional<ShiftEnd> shiftEnd;
    private final Optional<ShiftStart> shiftStart;

    private ShiftChangeRemind(Id deviceId, WorkShift shiftEnd, WorkShift shiftStart) {
        this.shiftEnd = Optional.ofNullable(shiftEnd).map(ShiftEnd::new);
        this.shiftStart = Optional.ofNullable(shiftStart).map(ShiftStart::new);
        this.deviceId = deviceId;
        ShiftChangeRemindContainingSpecification.getInstance().check(this);
        ShiftChangeRemindSwitchTimingSpecification.getInstance().check(this);
    }

    public static ShiftChangeRemind ofEnd(Id deviceId, WorkShift shiftThatEnds) {
        return new ShiftChangeRemind(deviceId, shiftThatEnds, null);
    }

    public static ShiftChangeRemind ofStart(Id deviceId, WorkShift shiftThatStarts) {
        return new ShiftChangeRemind(deviceId, null, shiftThatStarts);
    }

    public static ShiftChangeRemind ofSwitch(Id deviceId, WorkShift shiftThatEnds, WorkShift shiftThatStarts) {
        return new ShiftChangeRemind(deviceId, shiftThatEnds, shiftThatStarts);
    }

    @EqualsAndHashCode(callSuper = true)
    @Getter
    public static class ShiftStart extends ShiftChange {
        // [Q] are inner static classes ok when they make sens only of using in context of 'outer' class
        private ShiftStart(WorkShift workShift) {
            super(workShift);
        }
    }


    @EqualsAndHashCode(callSuper = true)
    @Getter
    public static class ShiftEnd extends ShiftChange {
        private ShiftEnd(WorkShift workShift) {
            super(workShift);
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @EqualsAndHashCode
    @Getter
    public static abstract class ShiftChange {
        private final WorkShift workShift;
    }
}
