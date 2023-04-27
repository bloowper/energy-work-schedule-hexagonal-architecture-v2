package orchowski.tomasz.energyworkschedule.domain.entity;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(staticName = "basedOn")
class ShiftChangeRemindCreator {
    private final Id deviceId;
    private final WorkSchedule workSchedule;

    public List<ShiftChangeRemind> createReminders() {
        // TODO refactor!!! it's practically unreadable.
        List<ShiftChangeRemind> shiftChangeReminds = new ArrayList<>();
        List<WorkShift> workShifts = workSchedule.getWorkShifts();
        for (int i = 0; i < workShifts.size(); i++) {
            WorkShift workShift = workShifts.get(i);
            if (isLastRemindIsBeginningOfShift(shiftChangeReminds, workShift)) {
                ShiftChangeRemind previousShiftChangeRemind = shiftChangeReminds.get(shiftChangeReminds.size() - 1);
                ShiftChangeRemind shiftSwitch = previousShiftChangeRemind.changeEndToSwitch(workShift);
                shiftChangeReminds.set(i, shiftSwitch);
                shiftChangeReminds.add(ShiftChangeRemind.ofEnd(deviceId, workShift));
                continue;
            }
            shiftChangeReminds.add(ShiftChangeRemind.ofStart(deviceId, workShift));
            shiftChangeReminds.add(ShiftChangeRemind.ofEnd(deviceId, workShift));
        }
        return shiftChangeReminds;
    }

    private boolean isLastRemindIsBeginningOfShift(List<ShiftChangeRemind> shiftChangeReminds, WorkShift workShift) {
        Optional<ShiftChangeRemind> lastShiftChangeRemind = getLast(shiftChangeReminds);
        return lastShiftChangeRemind.isPresent() &&
                lastShiftChangeRemind.get().getType().equals(ShiftChangeRemind.RemindType.END_OF_SHIFT) &&
                lastShiftChangeRemind.get().getDate().equals(workShift.getStart());
    }

    private Optional<ShiftChangeRemind> getLast(List<ShiftChangeRemind> shiftChangeReminds) {
        if (!shiftChangeReminds.isEmpty()) {
            return Optional.of(shiftChangeReminds.get(shiftChangeReminds.size() - 1));
        } else {
            return Optional.empty();
        }
    }

}
