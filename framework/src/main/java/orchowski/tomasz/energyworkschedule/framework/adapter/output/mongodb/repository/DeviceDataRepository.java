package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.repository;


import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.DeviceData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DeviceDataRepository extends MongoRepository<DeviceData, UUID> {

}
