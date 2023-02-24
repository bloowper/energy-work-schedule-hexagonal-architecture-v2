package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkShiftRestView {
    private TimePeriodRestView timePeriod;
    private RuleRestView rule;
}
