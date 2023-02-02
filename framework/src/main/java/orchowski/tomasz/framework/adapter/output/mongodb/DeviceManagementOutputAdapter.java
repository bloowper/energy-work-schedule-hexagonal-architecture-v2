package orchowski.tomasz.framework.adapter.output.mongodb;

import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;

class DeviceManagementOutputAdapter implements DeviceManagementOutputPort {
    //TODO
    @Override
    public Device fetchDevice(Id id) {
        return null;
    }

    @Override
    public Device removeDevice(Id id) {
        return null;
    }

    @Override
    public Device persistDevice(Device device) {
        return null;
    }
}
