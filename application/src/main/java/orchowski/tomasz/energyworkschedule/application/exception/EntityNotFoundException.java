package orchowski.tomasz.energyworkschedule.application.exception;

import orchowski.tomasz.energyworkschedule.domain.value.Id;

import java.util.NoSuchElementException;

public class EntityNotFoundException extends NoSuchElementException {
    public EntityNotFoundException(Class<?> type, Id id) {
        super("Not found [%s] with id: [%s]".formatted(type.getSimpleName(), id));
    }
}
