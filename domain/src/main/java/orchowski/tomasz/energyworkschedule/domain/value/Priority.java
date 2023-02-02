package orchowski.tomasz.energyworkschedule.domain.value;

import lombok.Data;
import orchowski.tomasz.energyworkschedule.domain.specification.PrioritySpecification;

@Data
public class Priority {
    private final Integer value;

    public Priority(Integer value) {
        this.value = value;
        PrioritySpecification.getInstance().check(this);
    }

    public Priority increase(Integer value) {
        return new Priority(value + value);
    }

    public Priority decrease(Integer value) {
        return new Priority(value - value);
    }
}
