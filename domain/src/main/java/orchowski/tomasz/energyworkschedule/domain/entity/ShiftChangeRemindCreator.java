package orchowski.tomasz.energyworkschedule.domain.entity;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(staticName = "basedOn")
class ShiftChangeRemindCreator {
    private final Id deviceId;
    private final WorkSchedule workSchedule;

    public List<ShiftChangeRemind> createReminders() {
        // TODO refactor!!! it's practically unreadable. Total garbage :D
        List<ShiftChangeRemind> shiftChangeReminds = new ArrayList<>();
        List<WorkShift> workShifts = workSchedule.getWorkShifts();
        for (int i = 0; i < workShifts.size(); i++) {
            WorkShift workShift = workShifts.get(i);
            if (i >= 1 &&
                    getPrevious(shiftChangeReminds).getType().equals(ShiftChangeRemind.RemindType.END_OF_SHIFT) &&
                    getPrevious(shiftChangeReminds).getShiftEnd().get().getWorkShift().getDuration().getEnd().equals(workShift.getDuration().getStart())
            ) {
                ShiftChangeRemind previousShiftChangeRemind = shiftChangeReminds.get(shiftChangeReminds.size() - 1);
                ShiftChangeRemind shiftSwitch = previousShiftChangeRemind.changeToSwitch(workShift);
                shiftChangeReminds.set(i, shiftSwitch);
                shiftChangeReminds.add(ShiftChangeRemind.ofEnd(deviceId, workShift));
                continue;
            }
            shiftChangeReminds.add(ShiftChangeRemind.ofStart(deviceId, workShift));
            shiftChangeReminds.add(ShiftChangeRemind.ofEnd(deviceId, workShift));
        }
        return shiftChangeReminds;
    }

    private ShiftChangeRemind getPrevious(List<ShiftChangeRemind> shiftChangeReminds) {
        return shiftChangeReminds.get(shiftChangeReminds.size() - 1);
    }

}
