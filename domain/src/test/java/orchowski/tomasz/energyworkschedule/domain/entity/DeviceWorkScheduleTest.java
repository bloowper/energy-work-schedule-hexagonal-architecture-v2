package orchowski.tomasz.energyworkschedule.domain.entity;


import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeviceWorkScheduleTest {
    private static final Instant i1 = LocalDateTime.of(2000, 1, 1, 0, 0).toInstant(ZoneOffset.UTC);
    private static final Instant i2 = LocalDateTime.of(2000, 1, 2, 0, 0).toInstant(ZoneOffset.UTC);
    private static final Instant i3 = LocalDateTime.of(2000, 1, 3, 0, 0).toInstant(ZoneOffset.UTC);
    private static final Instant i4 = LocalDateTime.of(2000, 1, 4, 0, 0).toInstant(ZoneOffset.UTC);
    private static final Instant i5 = LocalDateTime.of(2000, 1, 5, 0, 0).toInstant(ZoneOffset.UTC);
    private static final Instant i6 = LocalDateTime.of(2000, 1, 6, 0, 0).toInstant(ZoneOffset.UTC);
    private static final MaxPowerUsageRule LOW_USAGE_RULE = new MaxPowerUsageRule(1_000.);
    private static final MaxPowerUsageRule MEDIUM_USAGE_RULE = new MaxPowerUsageRule(3_000.);
    private static final MaxPowerUsageRule HIGH_USAGE_RULE = new MaxPowerUsageRule(12_000.);


    private static final Priority LOW_PRIORITY = new Priority(1000);
    private static final Priority MEDIUM_PRIORITY = new Priority(2000);
    private static final Priority HIGH_PRIORITY = new Priority(3000);


    @Test
    void shouldCrateValidWorkScheduleFromSinglePolicy() {
        // given
        /*
         * ...<----------->........... policy1 | prio MED   | i2-i2
         * ---1-----------2----------- (instants desc)
         *
         *  */
        Device device = new Device(Id.generateNew());
        device.addNewPolicy(new Policy(
                new TimePeriod(i1, i2),
                MEDIUM_PRIORITY,
                HIGH_USAGE_RULE
        ));

        WorkSchedule validWorkSchedule = new WorkSchedule(
                new TimePeriod(i1, i2),
                List.of(
                        new WorkShift(
                                new TimePeriod(i1, i2),
                                HIGH_USAGE_RULE
                        )
                )
        );

        // when
        WorkSchedule workSchedule = device.generateWorkSchedule();

        // then
        assertEquals(validWorkSchedule, workSchedule);
    }

    @Test
    void shouldCreateValidWorkScheduleFromTwoNotOverlyingPolicy() {
        // given
        /*
         * ..<---->................... policy1 | prio MED   | i2-i2
         * ............<----->........ policy2 | prio MED   | i3-i4
         * --1----2----3-----4-------- (instants desc)
         *
         *  */
        Device device = new Device(Id.generateNew());
        device.addNewPolicy(new Policy(
                new TimePeriod(i1, i2),
                MEDIUM_PRIORITY,
                HIGH_USAGE_RULE
        ));
        device.addNewPolicy(new Policy(
                new TimePeriod(i3, i4),
                MEDIUM_PRIORITY,
                MEDIUM_USAGE_RULE
        ));

        WorkSchedule validWorkSchedule = new WorkSchedule(
                new TimePeriod(i1, i4),
                List.of(
                        new WorkShift(
                                new TimePeriod(i1, i2),
                                HIGH_USAGE_RULE
                        ),
                        new WorkShift(
                                new TimePeriod(i3, i4),
                                MEDIUM_USAGE_RULE
                        )
                )
        );

        // when
        WorkSchedule workSchedule = device.generateWorkSchedule();

        // then
        assertEquals(validWorkSchedule, workSchedule);
    }

    @Test
    void shouldCreateValidWorkScheduleFromTouchingPolicies() {
        // given
        /*
         * .<----------->............. policy1 | prio MED   | i2-i2
         * .............<--------->... policy2 | prio MED   | i3-i4
         * -1-----------2---------3--- (instants desc)
         *
         * -|<----w1--->|<--w2--->|---
         *
         *  */
        Device device = new Device(Id.generateNew());
        device.addNewPolicy(new Policy(
                new TimePeriod(i1, i2),
                MEDIUM_PRIORITY,
                HIGH_USAGE_RULE
        ));
        device.addNewPolicy(new Policy(
                new TimePeriod(i2, i3),
                MEDIUM_PRIORITY,
                LOW_USAGE_RULE
        ));

        WorkSchedule validWorkSchedule = new WorkSchedule(
                new TimePeriod(i1, i3),
                List.of(
                        new WorkShift(
                                new TimePeriod(i1, i2),
                                HIGH_USAGE_RULE
                        ),
                        new WorkShift(
                                new TimePeriod(i2, i3),
                                LOW_USAGE_RULE
                        )
                )
        );

        // when
        WorkSchedule workSchedule = device.generateWorkSchedule();

        // then
        assertEquals(validWorkSchedule, workSchedule);
    }

    @Test
    void shouldCreateValidWorkScheduleWith3siftsFromTwoOverlyingPolicies() {
        // given
        /*
         * ..<------------------->.... policy1 | prio MED    | i2-i4
         * .......<--------->......... policy2 | prio HIGH   | i2-i3
         * --1----2---------3----4---- (instants desc)
         *
         * --|<w1>|<---w2-->|<w1>|---   output sum of policies(time)
         *
         *  */

        Device device = new Device(Id.generateNew());
        device.addNewPolicy(new Policy(
                new TimePeriod(i1, i4),
                MEDIUM_PRIORITY,
                HIGH_USAGE_RULE
        ));
        device.addNewPolicy(new Policy(
                new TimePeriod(i2, i3),
                HIGH_PRIORITY,
                LOW_USAGE_RULE
        ));

        WorkSchedule validWorkSchedule = new WorkSchedule(
                new TimePeriod(i1, i4),
                List.of(
                        new WorkShift(
                                new TimePeriod(i1, i2),
                                HIGH_USAGE_RULE
                        ),
                        new WorkShift(
                                new TimePeriod(i2, i3),
                                LOW_USAGE_RULE
                        ),
                        new WorkShift(
                                new TimePeriod(i3, i4),
                                HIGH_USAGE_RULE
                        )
                )
        );

        // when
        WorkSchedule workSchedule = device.generateWorkSchedule();

        // then
        assertEquals(validWorkSchedule, workSchedule);
    }

    @Test
    void shouldCreateValidScheduleWhenPolicyIsHiddenByOtherWithHigherPriority() {
        // given
        /*
         * ...<-------------------->.. policy1 | prio HIGH   | i2-i4
         * ........<-------->......... policy2 | prio LOW    | i2-i3
         * ---1----2--------3------4-- (instants desc)
         *
         * ---|<-------w1--------->|--
         *
         *  */
        Device device = new Device(Id.generateNew());
        device.addNewPolicy(new Policy(
                new TimePeriod(i1, i4),
                HIGH_PRIORITY,
                HIGH_USAGE_RULE
        ));
        device.addNewPolicy(new Policy(
                new TimePeriod(i2, i3),
                LOW_PRIORITY,
                LOW_USAGE_RULE
        ));

        WorkSchedule validWorkSchedule = new WorkSchedule(
                new TimePeriod(i1, i4),
                List.of(
                        new WorkShift(
                                new TimePeriod(i1, i4),
                                HIGH_USAGE_RULE
                        )
                )
        );


        // when
        WorkSchedule workSchedule = device.generateWorkSchedule();

        // then
        assertEquals(validWorkSchedule, workSchedule);
    }


    @Test
    void example() {
        // given
        Device device = new Device(Id.generateNew());
        device.addNewPolicy(new Policy(
                new TimePeriod(i1, i2),
                MEDIUM_PRIORITY,
                HIGH_USAGE_RULE
        ));

        WorkSchedule validWorkSchedule = new WorkSchedule(
                new TimePeriod(i1, i2),
                List.of(
                        new WorkShift(
                                new TimePeriod(i1, i2),
                                HIGH_USAGE_RULE
                        )
                )
        );

        // when
        WorkSchedule workSchedule = device.generateWorkSchedule();

        // then
        assertEquals(validWorkSchedule, workSchedule);
    }

}
