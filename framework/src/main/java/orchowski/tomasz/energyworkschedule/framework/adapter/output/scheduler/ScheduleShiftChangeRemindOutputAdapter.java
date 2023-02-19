package orchowski.tomasz.energyworkschedule.framework.adapter.output.scheduler;

import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.energyworkschedule.application.port.output.ScheduleShiftChangeRemindOutputPort;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
class ScheduleShiftChangeRemindOutputAdapter implements ScheduleShiftChangeRemindOutputPort {

    @Override
    public void scheduleReminders(List<ShiftChangeRemind> shiftChangeReminds) {
        log.info("Scheduling reminders: {}", shiftChangeReminds);
    }

    @Override
    public void removeRemindersForDevice(Id deviceId) {
        log.info("Removing reminders for device: {}", deviceId.getUuid());
    }
}
