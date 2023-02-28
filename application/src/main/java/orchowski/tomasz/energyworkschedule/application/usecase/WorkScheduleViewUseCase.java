package orchowski.tomasz.energyworkschedule.application.usecase;

import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;

import java.util.Optional;

public interface WorkScheduleViewUseCase {

    Optional<WorkSchedule> getWorkScheduleForDevice(Id deviceId);

}
