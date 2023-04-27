package orchowski.tomasz.energyworkschedule.domain.entity;

import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.value.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShiftChangeRemindTest {


    @Test
    void shouldCreateRemindersFromSingleShift() {
        // given
        Id deviceId = Id.generateNew();
        Device device = new Device(deviceId);
        Instant now = Instant.now();
        device.addNewPolicy(new Policy(
                Id.generateNew(),
                new TimePeriod(
                        now,
                        now.plus(Duration.ofHours(2))
                ),
                new Priority(1),
                new MaxPowerUsageRule(450.)
        ));
        WorkShift workShift = device.generateWorkSchedule().getWorkShifts().get(0);

        // when
        List<ShiftChangeRemind> shiftChangeReminds = device.generateShiftChangeReminds();

        // then
        System.out.println(ShiftChangeRemind.ofStart(deviceId, workShift));
        System.out.println(shiftChangeReminds.get(0));
        assertThat(shiftChangeReminds.get(0)).isEqualTo(ShiftChangeRemind.ofStart(deviceId, workShift));

        assertThat(shiftChangeReminds)
                .containsExactly(
                        ShiftChangeRemind.ofStart(deviceId, workShift),
                        ShiftChangeRemind.ofEnd(deviceId, workShift)
                ).hasSize(2);

    }

    @Test
    void shouldCreateRemindersFromShiftsWithBreakBetween() {
        // given
        Id deviceId = Id.generateNew();
        Device device = new Device(deviceId);
        Instant now = Instant.now();
        device.addNewPolicy(new Policy(
                Id.generateNew(),
                new TimePeriod(
                        now,
                        now.plus(Duration.ofHours(2))
                ),
                new Priority(1),
                new MaxPowerUsageRule(450.)
        ));
        device.addNewPolicy(new Policy(
                Id.generateNew(),
                new TimePeriod(
                        now.plus(Duration.ofHours(3)),
                        now.plus(Duration.ofHours(4))
                ),
                new Priority(1),
                new MaxPowerUsageRule(450.)
        ));

        WorkShift firstWorkShift = device.generateWorkSchedule().getWorkShifts().get(0);
        WorkShift secondWorkShift = device.generateWorkSchedule().getWorkShifts().get(1);

        // when
        List<ShiftChangeRemind> shiftChangeReminds = device.generateShiftChangeReminds();

        // then
        assertThat(shiftChangeReminds)
                .containsExactly(
                        ShiftChangeRemind.ofStart(deviceId, firstWorkShift),
                        ShiftChangeRemind.ofEnd(deviceId, firstWorkShift),
                        ShiftChangeRemind.ofStart(deviceId, secondWorkShift),
                        ShiftChangeRemind.ofEnd(deviceId, secondWorkShift)
                ).hasSize(4);

    }

    @Test
    void shouldCreateRemindersFromShiftsThatFollowEachOther() {
        // given
        Id deviceId = Id.generateNew();
        Device device = new Device(deviceId);
        Instant now = Instant.now();
        device.addNewPolicy(new Policy(
                Id.generateNew(),
                new TimePeriod(
                        now,
                        now.plus(Duration.ofHours(2))
                ),
                new Priority(1),
                new MaxPowerUsageRule(450.)
        ));
        device.addNewPolicy(new Policy(
                Id.generateNew(),
                new TimePeriod(
                        now.plus(Duration.ofHours(2)),
                        now.plus(Duration.ofHours(3))
                ),
                new Priority(1),
                new MaxPowerUsageRule(450.)
        ));
        WorkSchedule workSchedule = device.generateWorkSchedule();
        WorkShift firstWorkShift = workSchedule.getWorkShifts().get(0);
        WorkShift secondWorkShift = workSchedule.getWorkShifts().get(1);

        // when
        List<ShiftChangeRemind> shiftChangeReminds = device.generateShiftChangeReminds();

        // then
        assertThat(shiftChangeReminds)
                .containsExactly(
                        ShiftChangeRemind.ofStart(deviceId, firstWorkShift),
                        ShiftChangeRemind.ofSwitch(deviceId, firstWorkShift, secondWorkShift),
                        ShiftChangeRemind.ofEnd(deviceId, secondWorkShift)
                ).hasSize(3);

    }
}
