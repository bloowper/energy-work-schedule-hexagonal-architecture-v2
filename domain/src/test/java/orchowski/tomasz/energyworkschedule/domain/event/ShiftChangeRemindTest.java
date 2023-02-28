package orchowski.tomasz.energyworkschedule.domain.event;

import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ShiftChangeRemindTest {

    @Test
    void shouldNotBeAbleToCreateARemindWithoutWorkShifts() {
        // given
        Id deviceId = Id.generateNew();
        WorkShift shiftThatEnds = null;
        WorkShift shiftThatStarts = null;

        // then
        assertThrows(GenericSpecificationException.class, () -> {
            ShiftChangeRemind.ofStart(deviceId, shiftThatStarts);
        });
        assertThrows(GenericSpecificationException.class, () -> {
            ShiftChangeRemind.ofEnd(deviceId, shiftThatEnds);
        });
        assertThrows(GenericSpecificationException.class, () -> {
            ShiftChangeRemind.ofSwitch(deviceId, shiftThatEnds, shiftThatStarts);
        });
    }

    @Test
    void shouldNotBeAbleToCreateReminderOfShiftChangeWithShiftsThatNotFollow() {
        // given
        Id deviceId = Id.generateNew();
        WorkShift shiftThatEnds = new WorkShift(
                new TimePeriod(
                        Instant.now(),
                        Instant.now().plus(Duration.ofHours(2))
                ),
                new MaxPowerUsageRule(20.)
        );
        WorkShift shiftThatStarts = new WorkShift(
                new TimePeriod(
                        Instant.now().plus(Duration.ofHours(3)),
                        Instant.now().plus(Duration.ofHours(4))
                ),
                new MaxPowerUsageRule(20.)
        );

        // then
        assertThrows(GenericSpecificationException.class, () -> {
            ShiftChangeRemind.ofSwitch(deviceId, shiftThatEnds, shiftThatStarts);
        });
    }

    @Test
    void shouldBeAbleToCreateStartShiftRemind() {
        // given
        Id deviceId = Id.generateNew();
        WorkShift shiftThatStarts = new WorkShift(
                new TimePeriod(
                        Instant.now(),
                        Instant.now().plus(Duration.ofHours(2))
                ),
                new MaxPowerUsageRule(20.)
        );

        // then
        ShiftChangeRemind.ofStart(deviceId, shiftThatStarts);
    }

    @Test
    void shouldBeAbleToCreateEndShiftRemind() {
        // given
        Id deviceId = Id.generateNew();
        WorkShift shiftThatEnds = new WorkShift(
                new TimePeriod(
                        Instant.now(),
                        Instant.now().plus(Duration.ofHours(2))
                ),
                new MaxPowerUsageRule(20.)
        );

        // then
        ShiftChangeRemind.ofEnd(deviceId, shiftThatEnds);
    }

    @Test
    void shouldBeAbleToCreateSwitchShiftRemind() {
        // given
        Id deviceId = Id.generateNew();
        Instant now = Instant.now();
        WorkShift shiftThatEnds = new WorkShift(
                new TimePeriod(
                        now,
                        now.plus(Duration.ofHours(2))
                ),
                new MaxPowerUsageRule(20.)
        );
        WorkShift shiftThatStarts = new WorkShift(
                new TimePeriod(
                        now.plus(Duration.ofHours(2)),
                        now.plus(Duration.ofHours(4))
                ),
                new MaxPowerUsageRule(20.)
        );

        // then
        ShiftChangeRemind.ofSwitch(deviceId, shiftThatEnds, shiftThatStarts);
    }
}
