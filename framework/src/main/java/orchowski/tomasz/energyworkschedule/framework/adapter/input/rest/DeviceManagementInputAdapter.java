package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import orchowski.tomasz.energyworkschedule.application.usecase.DeviceManagementUseCase;
import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.mapper.RestViewMapper;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.DeviceRestView;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/device")
class DeviceManagementInputAdapter {
    private final DeviceManagementUseCase deviceManagementUseCase;
    private final RestViewMapper restViewMapper;

    @PutMapping("/{device-uuid}")
    DeviceRestView createDevice(@PathVariable("device-uuid") String deviceUuid) {
        //[Q] is it ok to have logic like that in input adapter level?
        // should i create separate layer? Or it is ok in ports and adapters arch
        Device device = deviceManagementUseCase.persistDevice(deviceManagementUseCase.createDevice(Id.withId(deviceUuid)));
        return restViewMapper.toView(device);
    }

    @GetMapping("/{device-uuid}")
    Optional<DeviceRestView> fetchDevice(@PathVariable("device-uuid") String deviceUuid) {
        return deviceManagementUseCase.fetchDevice(Id.withId(deviceUuid))
                .map(restViewMapper::toView);
    }

    @DeleteMapping("/{device-uuid}")
    void removeDevice(@PathVariable("device-uuid") String deviceUuid) {
        deviceManagementUseCase.removeDevice(Id.withId(deviceUuid));
    }
}
