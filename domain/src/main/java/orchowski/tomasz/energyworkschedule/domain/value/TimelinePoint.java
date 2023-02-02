package orchowski.tomasz.energyworkschedule.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
public class TimelinePoint {
    private final Instant instant;
    private final Type type;
    private final Policy originPolicy;

    @Override
    public String toString() {
        return "Point{" +
                "instant=" + instant +
                ", type=" + type +
                ", priority=" + originPolicy.getPriority() +
                ", value=" + originPolicy.getMaxPowerUsageRule().getValue() +
                '}';
    }

    public enum Type {
        START,
        END
    }
}
