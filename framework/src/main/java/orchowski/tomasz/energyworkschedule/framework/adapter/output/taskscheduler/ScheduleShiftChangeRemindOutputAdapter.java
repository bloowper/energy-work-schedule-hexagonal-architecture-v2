package orchowski.tomasz.energyworkschedule.framework.adapter.output.taskscheduler;

import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.energyworkschedule.application.port.output.ScheduleShiftChangeRemindOutputPort;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.Collectors;

@EnableScheduling
@Component
@Slf4j
class ScheduleShiftChangeRemindOutputAdapter implements ScheduleShiftChangeRemindOutputPort {
    /* TODO change for tool that could be used in production
     *   TaskScheduler from spring is not good idea, used just for develop purpose
     * */
    private final TaskScheduler taskScheduler;
    private final Map<Id, List<? extends ScheduledFuture<?>>> scheduledFeatureMap;

    ScheduleShiftChangeRemindOutputAdapter(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
        scheduledFeatureMap = new ConcurrentHashMap<>();
    }

    @Override
    public void scheduleReminders(List<ShiftChangeRemind> shiftChangeReminds) {
        log.info("Scheduling reminders: {}", shiftChangeReminds);
        Instant now = Instant.now();
        List<? extends ScheduledFuture<?>> scheduledFutures = shiftChangeReminds.stream()
                .filter(shiftChangeRemind ->
                        shiftChangeRemind.getDate().isAfter(now)
                )
                .map(RemindJob::new)
                .map(remindJob ->
                        taskScheduler.schedule(
                                remindJob,
                                remindJob.getShiftChangeRemind().getDate()
                        )
                ).collect(Collectors.toList());
        Id deviceId = shiftChangeReminds.get(0).getDeviceId(); // business flow does not allow this list to be empty
        scheduledFeatureMap.put(deviceId, scheduledFutures);
    }

    @Override
    public void removeRemindersForDevice(Id deviceId) {
        log.info("Removing reminders for device: {}", deviceId.getUuid());
        if (scheduledFeatureMap.containsKey(deviceId)) {
            scheduledFeatureMap.get(deviceId).forEach(scheduledFuture -> scheduledFuture.cancel(false));
            scheduledFeatureMap.remove(deviceId);
        }
    }
}
