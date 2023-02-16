package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.usecase.WorkScheduleViewUseCase;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import org.mapstruct.MappingTarget;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/device")
class WorkScheduleViewInputAdapter {
    private final WorkScheduleViewUseCase workScheduleViewUseCase;

    @GetMapping("/{device-uuid}/work-schedule")
    WorkSchedule fetchWorkSchedule(@PathVariable("device-uuid") String deviceUuid) {
        return workScheduleViewUseCase.getWorkScheduleForDevice(Id.withId(deviceUuid));
    }

}
