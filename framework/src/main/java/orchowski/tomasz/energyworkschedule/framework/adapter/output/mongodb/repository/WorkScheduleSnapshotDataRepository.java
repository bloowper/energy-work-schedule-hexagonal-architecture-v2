package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.repository;

import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.WorkScheduleSnapshotData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkScheduleSnapshotDataRepository extends MongoRepository<WorkScheduleSnapshotData, String> {
    Integer removeByDeviceId(String deviceId);
}
