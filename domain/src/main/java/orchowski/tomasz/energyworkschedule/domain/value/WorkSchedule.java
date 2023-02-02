package orchowski.tomasz.energyworkschedule.domain.value;

import lombok.Data;

import java.util.List;

@Data
public class WorkSchedule {
    private final TimePeriod duration;
    private final List<WorkShift> workShifts;
}
