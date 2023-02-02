package orchowski.tomasz.energyworkschedule.application.port.input;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.application.usecase.WorkScheduleViewUseCase;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

@RequiredArgsConstructor
public class WorkScheduleViewInputPort implements WorkScheduleViewUseCase {
    private final DeviceManagementOutputPort deviceManagementOutputPort;
    private final WorkScheduleSnapshotOutputPort workScheduleSnapshotOutputPort;


    @Override
    public Device fetchDevice(Id id) {
        return deviceManagementOutputPort.fetchDevice(id);
    }

    @Override
    public WorkSchedule getWorkScheduleForDevice(Device device) {
        return workScheduleSnapshotOutputPort.getWorkScheduleSnapshot(device.getId()).orElseGet(device::generateWorkSchedule);
    }
}
