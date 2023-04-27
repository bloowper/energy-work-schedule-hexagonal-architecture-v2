package orchowski.tomasz.energyworkschedule.application.shared;

import orchowski.tomasz.energyworkschedule.application.port.output.ScheduleShiftChangeRemindOutputPort;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.value.Id;

import java.util.List;

class ScheduleShiftChangeRemindStubOutputPort implements ScheduleShiftChangeRemindOutputPort {
    @Override
    public void scheduleReminders(List<ShiftChangeRemind> shiftChangeReminds) {

    }

    @Override
    public void removeRemindersForDevice(Id deviceId) {

    }
}
