package orchowski.tomasz.energyworkschedule.domain.value;

import lombok.Data;

import java.util.UUID;

@Data
public class Id {
    private final UUID uuid;

    public static Id withId(String id) {
        return new Id(UUID.fromString(id));
    }

    public static Id generateNew() {
        return new Id(UUID.randomUUID());
    }
}
