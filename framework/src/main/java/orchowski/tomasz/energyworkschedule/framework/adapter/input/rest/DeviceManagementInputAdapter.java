package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.usecase.DeviceManagementUseCase;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
class DeviceManagementInputAdapter {
    private final DeviceManagementUseCase deviceManagementUseCase;

}
