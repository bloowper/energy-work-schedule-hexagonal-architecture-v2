package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PolicyRestView {
    private String id;
    private TimePeriodRestView timePeriod;
    private Integer priority;
    private RuleRestView rule;
}
