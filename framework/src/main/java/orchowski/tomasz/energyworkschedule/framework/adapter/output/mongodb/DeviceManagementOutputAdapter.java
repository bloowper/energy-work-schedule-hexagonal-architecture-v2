package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb;

import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class DeviceManagementOutputAdapter implements DeviceManagementOutputPort {
    // TODO
    @Override
    public Optional<Device> fetchDevice(Id id) {
        return null;
    }

    @Override
    public Optional<Device> removeDevice(Id id) {
        return null;
    }

    @Override
    public Device persistDevice(Device device) {
        return null;
    }
}
