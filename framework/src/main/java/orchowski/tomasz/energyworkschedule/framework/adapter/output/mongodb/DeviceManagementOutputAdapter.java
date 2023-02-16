package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data.DeviceData;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.mapper.MongoDbMapper;
import orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.repository.DeviceDataRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class DeviceManagementOutputAdapter implements DeviceManagementOutputPort {
    private final DeviceDataRepository deviceDataRepository;
    private final MongoDbMapper mongoDbMapper;
    @Override
    public Optional<Device> fetchDevice(Id id) {
        return deviceDataRepository.findById(id.getUuid().toString())
                .map(mongoDbMapper::toDomain);
    }

    @Override
    public Optional<Device> removeDevice(Id id) {
        return deviceDataRepository.removeById(id.getUuid().toString())
                .map(mongoDbMapper::toDomain);
    }

    @Override
    public Device persistDevice(Device device) {
        DeviceData save = deviceDataRepository.save(mongoDbMapper.toData(device));
        return mongoDbMapper.toDomain(save);
    }

}
