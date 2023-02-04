package orchowski.tomasz.energyworkschedule.application.port.output;

import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

import java.util.Optional;

public interface WorkScheduleSnapshotOutputPort {

    Optional<WorkSchedule> getWorkScheduleSnapshot(Id deviceId);

    WorkSchedule persistWorkScheduleSnapshot(WorkSchedule workSchedule, Id deviceId);

    void removeForDevice(Id id);
}
