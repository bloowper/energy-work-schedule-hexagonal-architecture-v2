package orchowski.tomasz.energyworkschedule.application.port.input;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.application.usecase.DeviceManagementUseCase;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;

import java.util.Optional;

@RequiredArgsConstructor
public class DeviceManagementInputPort implements DeviceManagementUseCase {
    private final DeviceManagementOutputPort deviceManagementOutputPort;

    @Override
    public Device createDevice(Id id) {
        return new Device(id);
    }

    @Override
    public Optional<Device> removeDevice(Id id) {
        return deviceManagementOutputPort.removeDevice(id);
    }

    @Override
    public Optional<Device> fetchDevice(Id id) {
        return deviceManagementOutputPort.fetchDevice(id);
    }

    @Override
    public Device persistDevice(Device device) {
        return deviceManagementOutputPort.persistDevice(device);
    }

}
