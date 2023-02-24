package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WorkScheduleRestView {
    private TimePeriodRestView timePeriod;
    private List<WorkShiftRestView> workShifts;
}
