package orchowski.tomasz.energyworkschedule.application.port.input;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.port.output.WorkScheduleSnapshotOutputPort;
import orchowski.tomasz.energyworkschedule.application.usecase.WorkScheduleViewUseCase;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkScheduleViewInputPort implements WorkScheduleViewUseCase {
    private final WorkScheduleSnapshotOutputPort workScheduleSnapshotOutputPort;

    @Override
    public Optional<WorkSchedule> getWorkScheduleForDevice(Id deviceId) {
        return workScheduleSnapshotOutputPort.getWorkScheduleSnapshot(deviceId);
    }

}
