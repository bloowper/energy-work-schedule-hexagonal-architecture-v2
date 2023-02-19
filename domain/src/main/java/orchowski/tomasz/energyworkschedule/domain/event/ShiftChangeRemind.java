package orchowski.tomasz.energyworkschedule.domain.event;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import orchowski.tomasz.energyworkschedule.domain.specification.ShiftChangeRemindChangeToSwitchSpecification;
import orchowski.tomasz.energyworkschedule.domain.specification.ShiftChangeRemindContainingSpecification;
import orchowski.tomasz.energyworkschedule.domain.specification.ShiftChangeRemindDateSpecification;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;

import java.time.Instant;
import java.util.Optional;

@EqualsAndHashCode
@Getter
@ToString
public class ShiftChangeRemind {
    // [Q] is using optionals af fields is ok? How such design should looks like?
    private final Id deviceId;
    private final Instant remindDate;
    private final Optional<ShiftEnd> shiftThatEnds;
    private final Optional<ShiftStart> shiftThatStarts;

    private ShiftChangeRemind(Id deviceId, WorkShift shiftThatEnds, WorkShift shiftThatStarts) {
        this.shiftThatEnds = Optional.ofNullable(shiftThatEnds).map(ShiftEnd::new);
        this.shiftThatStarts = Optional.ofNullable(shiftThatStarts).map(ShiftStart::new);
        this.deviceId = deviceId;
        ShiftChangeRemindContainingSpecification.getInstance().check(this);
        ShiftChangeRemindDateSpecification.getInstance().check(this);
        if (shiftThatStarts != null) {
            remindDate = shiftThatStarts.getDuration().getStart();
        } else {
            remindDate = shiftThatEnds.getDuration().getEnd();
        }
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

    public ShiftChangeRemind changeToSwitch(WorkShift shiftThatStarts) {
        ShiftChangeRemindChangeToSwitchSpecification.getInstance().check(this);
        return ofSwitch(this.deviceId, this.shiftThatEnds.get().getWorkShift(), shiftThatStarts);
    }

    public RemindType getType() {
        if (shiftThatEnds.isPresent() && shiftThatStarts.isPresent()) {
            return RemindType.SWITCH_OF_SHIFT;
        } else if (shiftThatStarts.isPresent()) {
            return RemindType.START_OF_SHIFT;
        } else {
            return RemindType.END_OF_SHIFT;
        }
    }

    public Instant getDate() {
        return remindDate;
    }

    public enum RemindType {
        START_OF_SHIFT,
        END_OF_SHIFT,
        SWITCH_OF_SHIFT
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
        protected final WorkShift workShift;
    }
}
