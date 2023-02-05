package orchowski.tomasz.energyworkschedule.application.port.input

import orchowski.tomasz.energyworkschedule.application.shared.BaseSpecification
import orchowski.tomasz.energyworkschedule.domain.value.Id

class DeviceManagementInputPortTest extends BaseSpecification {

    def "Add new device"() {
        given: //Valid device
        def id = Id.generateNew()
        def device = deviceManagementUseCase.createDevice(id)

        when: //Device persisted
        deviceManagementUseCase.persistDevice(device)

        then: //Device should be accessible in system
        def fetchDevice = deviceManagementUseCase.fetchDevice(id)
        fetchDevice.get() != null
    }

}
