package orchowski.tomasz.energyworkschedule.domain.event;

import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShiftChangeRemindTest {

    @Test
    void shouldNotBeAbleToCreateARemindWithoutWorkShifts() {
        // given
        WorkShift shiftThatEnds = null;
        WorkShift shiftThatStarts = null;

        // then
        assertThrows(GenericSpecificationException.class, () -> {
            ShiftChangeRemind.ofStart(shiftThatStarts);
        });
        assertThrows(GenericSpecificationException.class, () -> {
            ShiftChangeRemind.ofEnd(shiftThatEnds);
        });
        assertThrows(GenericSpecificationException.class, () -> {
            ShiftChangeRemind.ofSwitch(shiftThatEnds, shiftThatStarts);
        });
    }

    @Test
    void shouldNotBeAbleToCreateReminderOfShiftChangeWithShiftsThatNotFollow() {
        // given
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
            ShiftChangeRemind.ofSwitch(shiftThatEnds, shiftThatStarts);
        });
    }

    @Test
    void shouldBeAbleToCreateStartShiftRemind() {
        // given
        WorkShift shiftThatStarts = new WorkShift(
                new TimePeriod(
                        Instant.now(),
                        Instant.now().plus(Duration.ofHours(2))
                ),
                new MaxPowerUsageRule(20.)
        );

        // then
        ShiftChangeRemind.ofStart(shiftThatStarts);
    }

    @Test
    void shouldBeAbleToCreateEndShiftRemind() {
        // given
        WorkShift shiftThatEnds = new WorkShift(
                new TimePeriod(
                        Instant.now(),
                        Instant.now().plus(Duration.ofHours(2))
                ),
                new MaxPowerUsageRule(20.)
        );

        // then
        ShiftChangeRemind.ofEnd(shiftThatEnds);
    }

    @Test
    void shouldBeAbleToCreateSwitchShiftRemind() {
        // given
        WorkShift shiftThatEnds = new WorkShift(
                new TimePeriod(
                        Instant.now(),
                        Instant.now().plus(Duration.ofHours(2))
                ),
                new MaxPowerUsageRule(20.)
        );
        WorkShift shiftThatStarts = new WorkShift(
                new TimePeriod(
                        Instant.now().plus(Duration.ofHours(2)),
                        Instant.now().plus(Duration.ofHours(4))
                ),
                new MaxPowerUsageRule(20.)
        );

        // then
        ShiftChangeRemind.ofSwitch(shiftThatEnds, shiftThatStarts);
    }
}
