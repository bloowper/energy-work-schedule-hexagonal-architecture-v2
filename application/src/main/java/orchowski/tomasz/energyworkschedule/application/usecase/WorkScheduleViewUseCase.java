package orchowski.tomasz.energyworkschedule.application.usecase;

import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

public interface WorkScheduleViewUseCase {
    Device fetchDevice(Id id);

    WorkSchedule getWorkScheduleForDevice(Device device);

}
