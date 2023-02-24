package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class TimePeriodRestView {
    private Instant start;
    private Instant end;
}
