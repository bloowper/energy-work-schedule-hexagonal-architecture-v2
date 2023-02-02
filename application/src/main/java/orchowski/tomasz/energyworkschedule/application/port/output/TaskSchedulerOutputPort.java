package orchowski.tomasz.energyworkschedule.application.port.output;

import orchowski.tomasz.energyworkschedule.domain.value.Id;

public interface TaskSchedulerOutputPort {
    void scheduleTask();

    void cancelTask(Id id);
}
