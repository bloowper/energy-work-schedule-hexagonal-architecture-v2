package orchowski.tomasz.energyworkschedule.application.usecase;

import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;

public interface DeviceManagementUseCase {
    Device createDevice(Id id);

    Device removeDevice(Id id);

    Device fetchDevice(Id id);

    Device persistDevice(Device device);

}
