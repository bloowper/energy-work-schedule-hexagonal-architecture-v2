package orchowski.tomasz.energyworkschedule.application.port.output;

import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;

public interface DeviceManagementOutputPort {

    Device fetchDevice(Id id);

    Device removeDevice(Id id);

    Device persistDevice(Device device);
}
