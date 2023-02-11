package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.usecase.WorkScheduleViewUseCase;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class WorkScheduleViewInputAdapter {
    private final WorkScheduleViewUseCase workScheduleViewUseCase;

}
