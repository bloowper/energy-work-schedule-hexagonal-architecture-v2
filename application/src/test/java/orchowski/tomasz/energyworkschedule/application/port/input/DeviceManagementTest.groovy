package orchowski.tomasz.energyworkschedule.application.port.input

import orchowski.tomasz.energyworkschedule.application.shared.BaseSpecification
import orchowski.tomasz.energyworkschedule.domain.value.Id

class DeviceManagementTest extends BaseSpecification {

    def "User should be able to add new device"() {
        given: "Valid device"
        def id = Id.generateNew()
        def device = deviceManagementUseCase.createDevice(id)

        when: "Device added by the user"
        deviceManagementUseCase.persistDevice(device)

        then: "Device should be accessible in system"
        def fetchDevice = deviceManagementUseCase.fetchDevice(id)
        fetchDevice.get() != null
    }

    def "User should be able to remove device"() {
        given: "System contain device"
        def id = Id.generateNew()
        def device = deviceManagementUseCase.createDevice(id)
        deviceManagementUseCase.persistDevice(device)

        when: "User remove device by id"
        deviceManagementUseCase.removeDevice(id)

        then: "System should not contain device"
        deviceManagementUseCase.fetchDevice(id).isEmpty()
    }

    def "User should be able to find device by id"() {
        given: "System contain device"
        def id = Id.generateNew()
        deviceManagementUseCase.persistDevice(deviceManagementUseCase.createDevice(id))

        when: "User search device by id"
        def device = deviceManagementUseCase.fetchDevice(id)

        then: "Device is returned to the user"
        device.isPresent()
    }
}
