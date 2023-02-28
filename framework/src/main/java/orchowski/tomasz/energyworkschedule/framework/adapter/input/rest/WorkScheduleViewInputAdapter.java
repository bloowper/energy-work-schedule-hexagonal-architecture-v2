package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.usecase.WorkScheduleViewUseCase;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.mapper.RestViewMapper;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.WorkScheduleRestView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/device")
class WorkScheduleViewInputAdapter {
    // [Q] package private VS separation of concerns (here packages view vs request) which should i choose?
    private final WorkScheduleViewUseCase workScheduleViewUseCase;
    private final RestViewMapper restViewMapper;

    @GetMapping("/{device-uuid}/work-schedule")
    Optional<WorkScheduleRestView> fetchWorkSchedule(@PathVariable("device-uuid") String deviceUuid) {
        return workScheduleViewUseCase.getWorkScheduleForDevice(Id.withId(deviceUuid))
                .map(restViewMapper::toView);
    }

}
