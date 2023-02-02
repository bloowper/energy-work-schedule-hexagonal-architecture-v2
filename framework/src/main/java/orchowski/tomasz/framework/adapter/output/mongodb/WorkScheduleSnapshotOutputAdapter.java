package orchowski.tomasz.framework.adapter.output.mongodb;

import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

import java.util.Optional;

class WorkScheduleSnapshotOutputAdapter implements WorkScheduleSnapshotOutputPort {
    // TODO
    @Override
    public Optional<WorkSchedule> getWorkScheduleSnapshot(Id deviceId) {
        return Optional.empty();
    }

    @Override
    public WorkSchedule persistWorkScheduleSnapshot(WorkSchedule workSchedule, Id deviceId) {
        return null;
    }
}
