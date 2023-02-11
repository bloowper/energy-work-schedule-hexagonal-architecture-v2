package orchowski.tomasz.energyworkschedule.application.port.input;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.exception.EntityNotFoundException;
import orchowski.tomasz.energyworkschedule.application.port.output.DeviceManagementOutputPort;
import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.application.usecase.WorkScheduleViewUseCase;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WorkScheduleViewInputPort implements WorkScheduleViewUseCase {
    private final DeviceManagementOutputPort deviceManagementOutputPort;
    private final WorkScheduleSnapshotOutputPort workScheduleSnapshotOutputPort;

    @Override
    public WorkSchedule getWorkScheduleForDevice(Id deviceId) {
        return workScheduleSnapshotOutputPort
                .getWorkScheduleSnapshot(deviceId)
                .orElseGet(() -> deviceManagementOutputPort.fetchDevice(deviceId).orElseThrow(() -> new EntityNotFoundException(Device.class, deviceId)).generateWorkSchedule());

    }

}
