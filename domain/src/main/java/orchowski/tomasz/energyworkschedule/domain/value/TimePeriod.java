package orchowski.tomasz.energyworkschedule.domain.value;

import lombok.Data;
import orchowski.tomasz.energyworkschedule.domain.specification.TimePeriodSpecification;

import java.time.Duration;
import java.time.Instant;

@Data
public class TimePeriod {
    private final Instant start;
    private final Instant end;

    public TimePeriod(Instant start, Instant end) {
        this.start = start;
        this.end = end;
        TimePeriodSpecification.getInstance().check(this);
    }

    public TimePeriod extend(Duration duration) {
        return new TimePeriod(this.start, this.end.plus(duration));
    }

    public TimePeriod reduce(Duration duration) {
        return new TimePeriod(this.start, this.end.minus(duration));
    }

    public TimePeriod move(Duration duration) {
        return new TimePeriod(this.start.plus(duration), this.end.plus(duration));
    }
}
