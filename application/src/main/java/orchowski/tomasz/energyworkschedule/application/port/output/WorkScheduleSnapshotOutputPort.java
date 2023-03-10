package orchowski.tomasz.energyworkschedule.application.port.output;

import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

import java.util.Optional;

public interface WorkScheduleSnapshotOutputPort {
    // TODO test moving creation of snapshot to framework layer

    Optional<WorkSchedule> getWorkScheduleSnapshot(Id deviceId);

    WorkSchedule persistWorkScheduleSnapshot(Id deviceId, WorkSchedule workSchedule);

    void removeSnapshotForDevice(Id id);
}
