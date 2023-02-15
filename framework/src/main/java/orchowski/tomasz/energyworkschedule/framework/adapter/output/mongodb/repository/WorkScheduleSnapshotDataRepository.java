package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.repository;

import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.WorkScheduleSnapshotData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface WorkScheduleSnapshotDataRepository extends MongoRepository<WorkScheduleSnapshotData, UUID> {

}
