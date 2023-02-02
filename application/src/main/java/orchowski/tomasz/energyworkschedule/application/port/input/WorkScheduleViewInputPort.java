package orchowski.tomasz.energyworkschedule.application.port.input;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.application.usecase.WorkScheduleViewUseCase;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

@RequiredArgsConstructor
public class WorkScheduleViewInputPort implements WorkScheduleViewUseCase {
    private final DeviceManagementOutputPort deviceManagementOutputPort;


    @Override
    public Device fetchDevice(Id id) {
        return deviceManagementOutputPort.fetchDevice(id);
    }

    @Override
    public WorkSchedule getWorkScheduleForDevice(Device device) {
        //[Q] seems to me to be a considerable excess of form over content
        return device.generateWorkSchedule();
    }
}
