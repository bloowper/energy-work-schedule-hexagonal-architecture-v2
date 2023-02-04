package orchowski.tomasz.energyworkschedule.application.port.output;

import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;

import java.util.Optional;

public interface DeviceManagementOutputPort {

    Optional<Device> fetchDevice(Id id);

    Optional<Device> removeDevice(Id id);

    Device persistDevice(Device device);
}
