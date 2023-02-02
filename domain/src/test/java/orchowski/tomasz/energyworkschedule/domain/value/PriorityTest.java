package orchowski.tomasz.energyworkschedule.domain.value;

import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PriorityTest {

    @Test
    void shouldThrowOnInvalidPriority() {
        // given
        int value = -1;

        // when
        // then
        assertThrows(GenericSpecificationException.class, () -> new Priority(value));
    }

    @Test
    void shouldCreateValueObject() {
        // given
        int value = 0;

        // then
        Priority priority = new Priority(value);

    }
}
