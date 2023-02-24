package orchowski.tomasz.energyworkschedule.domain.entity;

import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.Priority;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeviceTest {


    @Test
    void addNewPolicyShouldThrowOnOverlappingPolicy() {
        // given
        Device device = new Device(Id.generateNew());
        device.addNewPolicy(
                new Policy(
                        Id.generateNew(),
                        new TimePeriod(Instant.now(), Instant.now().plus(Duration.ofHours(2))),
                        new Priority(2),
                        new MaxPowerUsageRule(3_000.)
                )
        );

        Policy overlapingPolicy = new Policy(
                Id.generateNew(),
                new TimePeriod(Instant.now(), Instant.now().plus(Duration.ofHours(10))),
                new Priority(2),
                new MaxPowerUsageRule(3_000.)
        );

        // then
        assertThrows(GenericSpecificationException.class, () -> device.addNewPolicy(overlapingPolicy));
    }

    @Test
    void generateWorkScheduleShouldThrowWhenNoPoliciesAdded() {
        // given
        Device device = new Device(Id.generateNew());

        // then
        assertThrows(GenericSpecificationException.class, device::generateWorkSchedule);
    }

    @Test
    void addFirstPolicy() {
        // given
        Device device = new Device(Id.generateNew());
        Policy policy = new Policy(
                Id.generateNew(),
                new TimePeriod(Instant.now(), Instant.now().plus(Duration.ofHours(2))),
                new Priority(2),
                new MaxPowerUsageRule(3_000.)
        );

        // when
        device.addNewPolicy(policy);

        // then
        Collection<Policy> policies = device.getPoliciesView();
        assertThat(policies)
                .contains(policy);
    }



}
