package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.repository;


import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.DeviceData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DeviceDataRepository extends MongoRepository<DeviceData, String> {

    Optional<DeviceData> removeById(String id);
}
