package orchowski.tomasz.energyworkschedule.application.port.output;

import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

public interface WorkScheduleSnapshotOutputPort {

    WorkSchedule getWorkScheduleForDevice(Id deviceId);

    WorkSchedule persistWorkScheduleForDevice(WorkSchedule workSchedule, Id deviceId);
}
