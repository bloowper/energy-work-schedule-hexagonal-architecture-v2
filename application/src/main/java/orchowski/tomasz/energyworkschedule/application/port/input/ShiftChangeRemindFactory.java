package orchowski.tomasz.energyworkschedule.application.port.input;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(staticName = "basedOn")
class ShiftChangeRemindFactory {
    private final Id deviceId;
    private final WorkSchedule workSchedule;

    List<ShiftChangeRemind> createReminders() {
        // TODO refactor
        List<ShiftChangeRemind> shiftChangeReminds = new ArrayList<>();
        List<WorkShift> workShifts = workSchedule.getWorkShifts();
        for (int i = 0; i < workShifts.size(); i++) {
            WorkShift workShift = workShifts.get(i);

        }
        return shiftChangeReminds;
    }
}
