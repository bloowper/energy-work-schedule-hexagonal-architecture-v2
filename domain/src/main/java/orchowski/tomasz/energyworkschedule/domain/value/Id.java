package orchowski.tomasz.energyworkschedule.domain.value;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Id {
    private final UUID uuid;

    public static Id withId(String id) {
        return new Id(UUID.fromString(id));
    }

    public static Id generateNew() {
        return new Id(UUID.randomUUID());
    }
}
