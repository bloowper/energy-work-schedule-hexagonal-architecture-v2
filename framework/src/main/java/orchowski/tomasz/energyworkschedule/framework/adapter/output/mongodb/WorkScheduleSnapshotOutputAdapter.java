package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.WorkScheduleSnapshotData;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.mapper.MongoDbMapper;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.repository.WorkScheduleSnapshotDataRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class WorkScheduleSnapshotOutputAdapter implements WorkScheduleSnapshotOutputPort {
    private final WorkScheduleSnapshotDataRepository workScheduleSnapshotDataRepository;
    private final MongoDbMapper mongoDbMapper;

    @Override
    public Optional<WorkSchedule> getWorkScheduleSnapshot(Id deviceId) {
        return workScheduleSnapshotDataRepository.findById(deviceId.getUuid().toString())
                .map(mongoDbMapper::toDomain);
    }

    @Override
    public WorkSchedule persistWorkScheduleSnapshot(Id deviceId, WorkSchedule workSchedule) {
        WorkScheduleSnapshotData workScheduleSnapshotData = mongoDbMapper.toData(deviceId, workSchedule);
        WorkScheduleSnapshotData save = workScheduleSnapshotDataRepository.save(workScheduleSnapshotData);
        return mongoDbMapper.toDomain(save);
    }

    @Override
    public void removeSnapshotForDevice(Id id) {
        workScheduleSnapshotDataRepository.removeByDeviceId(id.getUuid().toString());
    }
}
