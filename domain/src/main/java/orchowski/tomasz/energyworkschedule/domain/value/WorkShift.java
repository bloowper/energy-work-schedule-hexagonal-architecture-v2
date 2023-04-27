package orchowski.tomasz.energyworkschedule.domain.value;

import lombok.Data;

import java.time.Instant;

@Data
public class WorkShift {
    private final TimePeriod duration;
    private final MaxPowerUsageRule rule;

    public WorkShift(TimePeriod duration, MaxPowerUsageRule rule) {
        this.duration = duration;
        this.rule = rule;
    }

    public Instant getStart() {
        return duration.getStart();
    }

    public Instant getEnd() {
        return duration.getEnd();
    }

    public static WorkShiftBuilder builder() {
        return new WorkShiftBuilder();
    }


    public static class WorkShiftBuilder {
        private Instant start;
        private Instant end;
        private MaxPowerUsageRule rule;

        WorkShiftBuilder() {
        }

        public WorkShiftBuilder start(Instant duration) {
            this.start = duration;
            return this;
        }

        public WorkShiftBuilder end(Instant duration) {
            this.end = duration;
            return this;
        }

        public WorkShiftBuilder rule(MaxPowerUsageRule rule) {
            this.rule = rule;
            return this;
        }

        public WorkShift build() {
            return new WorkShift(new TimePeriod(start, end), rule);
        }

    }
}
