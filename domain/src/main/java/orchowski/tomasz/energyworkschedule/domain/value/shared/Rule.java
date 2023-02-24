package orchowski.tomasz.energyworkschedule.domain.value.shared;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public abstract class Rule<T> {
    protected final T value;

    public abstract String getDescription();
}
