package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb;

import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
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

    @Override
    public void removeForDevice(Id id) {

    }
}
