package orchowski.tomasz.energyworkschedule.application.usecase;

import orchowski.tomasz.energyworkschedule.domain.value.Id;

public interface TaskExecutorUseCase {
    void scheduleTask();

    void cancelTask(Id id);

    void executeTask();
}
