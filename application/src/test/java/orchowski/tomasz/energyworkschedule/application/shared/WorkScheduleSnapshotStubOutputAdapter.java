package orchowski.tomasz.energyworkschedule.application.shared;

import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class WorkScheduleSnapshotStubOutputAdapter implements WorkScheduleSnapshotOutputPort {
    private final Map<Id, WorkSchedule> workScheduleMap = new ConcurrentHashMap<>();

    @Override
    public Optional<WorkSchedule> getWorkScheduleSnapshot(Id deviceId) {
        return Optional.ofNullable(workScheduleMap.get(deviceId));
    }

    @Override
    public WorkSchedule persistWorkScheduleSnapshot(WorkSchedule workSchedule, Id deviceId) {
        workScheduleMap.put(deviceId, workSchedule);
        return workSchedule;
    }

    @Override
    public void removeForDevice(Id id) {
        workScheduleMap.remove(id);
    }
}
