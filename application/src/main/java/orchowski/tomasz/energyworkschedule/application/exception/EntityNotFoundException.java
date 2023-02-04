package orchowski.tomasz.energyworkschedule.application.exception;

import orchowski.tomasz.energyworkschedule.domain.value.Id;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> type, Id id) {
        super("Not found [%s] with id: [%s]".formatted(type.getName(), id));
    }
}
