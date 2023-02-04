package orchowski.tomasz.energyworkschedule.application.usecase;

import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

public interface WorkScheduleViewUseCase {

    WorkSchedule getWorkScheduleForDevice(Id deviceId);

}
