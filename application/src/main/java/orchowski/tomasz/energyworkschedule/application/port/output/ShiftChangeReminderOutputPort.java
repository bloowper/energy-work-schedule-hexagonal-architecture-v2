package orchowski.tomasz.energyworkschedule.application.port.output;

import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;

import java.util.List;

public interface ShiftChangeReminderOutputPort {
    void scheduleReminders(List<ShiftChangeRemind> shiftChangeReminds);

}
