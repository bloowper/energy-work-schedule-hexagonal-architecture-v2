package orchowski.tomasz.energyworkschedule.application.port.input;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.application.port.output.ScheduleShiftChangeRemindOutputPort;
import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.application.usecase.DeviceManagementUseCase;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeviceManagementInputPort implements DeviceManagementUseCase {
    // [Q] Shouldn't the framework's hexagon be solely responsible for creating objects in the spring context?
    private final DeviceManagementOutputPort deviceManagementOutputPort;
    private final WorkScheduleSnapshotOutputPort workScheduleSnapshotOutputPort;
    private final ScheduleShiftChangeRemindOutputPort scheduleShiftChangeRemindOutputPort;

    @Override
    public Device createDevice(Id id) {
        return new Device(id);
    }

    @Override
    public Optional<Device> removeDevice(Id id) {
        workScheduleSnapshotOutputPort.removeSnapshotForDevice(id);
        scheduleShiftChangeRemindOutputPort.removeRemindersForDevice(id); //[Q] removing reminders is duplicated in two use cases impl is it ok? Or they should share this responsibilities in some service?
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
