package orchowski.tomasz.energyworkschedule.application.shared;

import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class DeviceManagementStubOutputAdapter implements DeviceManagementOutputPort {

    private final Map<Id, Device> devicesMap = new ConcurrentHashMap<>();

    @Override
    public Optional<Device> fetchDevice(Id id) {
        return Optional.ofNullable(devicesMap.get(id));
    }

    @Override
    public Optional<Device> removeDevice(Id id) {
        return Optional.ofNullable(devicesMap.remove(id));
    }

    @Override
    public Device persistDevice(Device device) {
        devicesMap.put(device.getId(), device);
        return device;
    }
}
