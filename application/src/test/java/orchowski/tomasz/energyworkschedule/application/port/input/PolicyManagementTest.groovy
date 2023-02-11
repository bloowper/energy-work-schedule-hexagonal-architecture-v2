package orchowski.tomasz.energyworkschedule.application.port.input

import orchowski.tomasz.energyworkschedule.application.shared.BaseSpecification
import orchowski.tomasz.energyworkschedule.domain.entity.Device
import orchowski.tomasz.energyworkschedule.domain.value.Id
import orchowski.tomasz.energyworkschedule.domain.value.Priority
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod

import java.time.Duration
import java.time.Instant

class PolicyManagementTest extends BaseSpecification {
    def Id deviceId;
    def Device device;

    def setup() {
        deviceId = Id.generateNew()
        device = deviceManagementUseCase.createDevice(deviceId)
        deviceManagementUseCase.persistDevice(device)
    }

    def "User should be able to add new policy to device"() {
        given: "System contain device"
        device

        and: "User has specified time period for new policy"
        def timePeriod = new TimePeriod(Instant.now(), Instant.now().plus(Duration.ofHours(10)))

        and: "User has specified priority for new policy"
        def priority = new Priority(100)


        and: "User has specified max allowed power usage for policy"
        def maxPowerUsage = 1500

        when: "User add policy to device"
        def newPolicy = policyManagementUseCase.createPowerUsagePolicy(
                timePeriod,
                priority,
                maxPowerUsage
        )
        policyManagementUseCase.addPolicyToDevice(device, newPolicy)

        then: "System should contain new policy"
        deviceManagementUseCase.fetchDevice(device.id).get()
                .policies.stream()
                .filter { (it == newPolicy) }
                .findAny().isPresent()
    }

    def "User should be able to remove policy"() {
        given: "System contain device with policy"
        def policy = policyManagementUseCase.createPowerUsagePolicy(
                new TimePeriod(Instant.now(), Instant.now().plus(Duration.ofHours(10))),
                new Priority(250),
                200
        )
        policyManagementUseCase.addPolicyToDevice(device, policy)

        and: "User has specified id of policy to deletion"
        def id = policy.getId()

        when: "User remove policy from device"
        policyManagementUseCase.removePolicyFromDevice(device, id)

        then: "System should not contain deleted policy"
        deviceManagementUseCase.fetchDevice(device.id).get()
                .policies.stream()
                .filter { it.id == id }
                .findAny().isEmpty()

    }

    def "User should be able to reschedule policy"() {
        //TODO
    }
}
