package orchowski.tomasz.energyworkschedule.application.port.output;

import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.value.Id;

import java.util.List;

public interface ScheduleShiftChangeRemindOutputPort {
    void scheduleReminders(List<ShiftChangeRemind> shiftChangeReminds);

    void removeRemindersForDevice(Id deviceId);
}
