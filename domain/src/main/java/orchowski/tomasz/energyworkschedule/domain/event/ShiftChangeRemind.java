package orchowski.tomasz.energyworkschedule.domain.event;

import lombok.*;
import orchowski.tomasz.energyworkschedule.domain.specification.ShiftChangeRemindChangeToSwitchSpecification;
import orchowski.tomasz.energyworkschedule.domain.specification.ShiftEndRemindSpecification;
import orchowski.tomasz.energyworkschedule.domain.specification.ShiftStartRemindSpecification;
import orchowski.tomasz.energyworkschedule.domain.specification.ShiftSwitchRemindSpecification;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;

import java.time.Instant;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public abstract sealed class ShiftChangeRemind {
    private final Id deviceId;

    public static ShiftChangeRemind ofEnd(Id deviceId, WorkShift shiftThatEnds) {
        return new ShiftEndRemind(deviceId, shiftThatEnds);
    }

    public static ShiftChangeRemind ofStart(Id deviceId, WorkShift shiftThatStarts) {
        return new ShiftStartRemind(deviceId, shiftThatStarts);
    }

    public static ShiftChangeRemind ofSwitch(Id deviceId, WorkShift shiftThatEnds, WorkShift shiftThatStarts) {
        return new ShiftSwitchRemind(deviceId, shiftThatEnds, shiftThatStarts);
    }

    public ShiftChangeRemind changeEndToSwitch(WorkShift shiftThatStarts) {
        ShiftChangeRemindChangeToSwitchSpecification.getInstance().check(this);
        return ofSwitch(this.deviceId, ((ShiftEndRemind) this).getShiftThatEnds(), shiftThatStarts);
    }

    public abstract RemindType getType();

    public abstract Instant getDate();

    @EqualsAndHashCode(callSuper = true)
    @Getter
    @ToString
    public static final class ShiftStartRemind extends ShiftChangeRemind {
        private final WorkShift shiftThatStarts;

        public ShiftStartRemind(Id deviceId, WorkShift shiftThatStarts) {
            super(deviceId);
            this.shiftThatStarts = shiftThatStarts;
            ShiftStartRemindSpecification.getInstance().check(this);
        }

        @Override
        public RemindType getType() {
            return RemindType.START_OF_SHIFT;
        }

        @Override
        public Instant getDate() {
            return shiftThatStarts.getStart();
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Getter
    @ToString
    public static final class ShiftEndRemind extends ShiftChangeRemind {
        private final WorkShift shiftThatEnds;

        public ShiftEndRemind(Id deviceId, WorkShift shiftThatEnds) {
            super(deviceId);
            this.shiftThatEnds = shiftThatEnds;
            ShiftEndRemindSpecification.getInstance().check(this);
        }

        @Override
        public RemindType getType() {
            return RemindType.END_OF_SHIFT;
        }

        @Override
        public Instant getDate() {
            return shiftThatEnds.getEnd();
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Getter
    @ToString
    public static final class ShiftSwitchRemind extends ShiftChangeRemind {
        private final WorkShift shiftThatEnds;
        private final WorkShift shiftThatStarts;

        public ShiftSwitchRemind(Id deviceId, WorkShift shiftThatEnds, WorkShift shiftThatStarts) {
            super(deviceId);
            this.shiftThatEnds = shiftThatEnds;
            this.shiftThatStarts = shiftThatStarts;
            ShiftSwitchRemindSpecification.getInstance().check(this);
        }

        @Override
        public RemindType getType() {
            return RemindType.SWITCH_OF_SHIFT;
        }

        @Override
        public Instant getDate() {
            return shiftThatEnds.getEnd();
        }
    }

    public enum RemindType {
        START_OF_SHIFT,
        END_OF_SHIFT,
        SWITCH_OF_SHIFT
    }

}
