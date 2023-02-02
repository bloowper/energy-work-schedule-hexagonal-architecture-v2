package orchowski.tomasz.energyworkschedule.domain.value;

import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimePeriodTest {

    @Test
    void shouldThrowExceptionOnInvalidArguments() {
        // given
        Instant start = Instant.now();
        Instant end = Instant.now().minus(Duration.ofMinutes(2));

        // then
        assertThrows(GenericSpecificationException.class, () -> new TimePeriod(start, end));
    }

    @Test
    void shouldExtendTimePeriod() {
        // given
        Instant start = Instant.now();
        Instant end = Instant.now().plus(Duration.ofHours(2));
        Duration extendDuration = Duration.ofHours(10);
        TimePeriod timePeriod = new TimePeriod(start, end);

        TimePeriod validTimePeriod = new TimePeriod(start, end.plus(extendDuration));

        // when
        TimePeriod extendedTimePeriod = timePeriod.extend(extendDuration);

        // then
        assertEquals(validTimePeriod, extendedTimePeriod);
    }

    @Test
    void shouldReduceTimePeriod() {
        // given
        Instant start = Instant.now();
        Instant end = Instant.now().plus(Duration.ofHours(2));
        Duration reduceDuration = Duration.ofHours(1);
        TimePeriod timePeriod = new TimePeriod(start, end);

        TimePeriod validTimePeriod = new TimePeriod(start, end.minus(reduceDuration));

        // when
        TimePeriod extendedTimePeriod = timePeriod.reduce(reduceDuration);

        // then
        assertEquals(validTimePeriod, extendedTimePeriod);
    }

    @Test
    void shouldThrowOnReducePeriod() {
        // given
        Instant start = Instant.now();
        Instant end = Instant.now().plus(Duration.ofHours(2));
        Duration reduceDuration = Duration.ofHours(40);
        TimePeriod timePeriod = new TimePeriod(start, end);

        // then
        assertThrows(GenericSpecificationException.class, () -> timePeriod.reduce(reduceDuration));
    }

    @Test
    void shouldMoveTimePeriod() {
        // given
        Instant start = Instant.now();
        Instant end = Instant.now().plus(Duration.ofHours(2));
        Duration moveDuration = Duration.ofHours(1);
        TimePeriod timePeriod = new TimePeriod(start, end);

        TimePeriod validTimePeriod = new TimePeriod(start.plus(moveDuration), end.plus(moveDuration));

        // when
        TimePeriod movedPeriod = timePeriod.move(moveDuration);

        // then
        assertEquals(validTimePeriod, movedPeriod);
    }

}
