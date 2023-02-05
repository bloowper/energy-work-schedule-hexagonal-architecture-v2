package orchowski.tomasz.energyworkschedule.application.port.input

import orchowski.tomasz.energyworkschedule.application.shared.BaseSpecification
import orchowski.tomasz.energyworkschedule.domain.value.Id
import orchowski.tomasz.energyworkschedule.domain.value.Priority
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod

import java.time.Duration
import java.time.Instant

class PolicyManagementTest extends BaseSpecification {

    def "User should be able to add new policy to device"() {
        given: "System contain device"
        def deviceId = Id.generateNew()
        deviceManagementUseCase.persistDevice(deviceManagementUseCase.createDevice(deviceId))

        and: "User has specified time period for new policy"
        def timePeriod = new TimePeriod(Instant.now(), Instant.now().plus(Duration.ofHours(10)))

        and: "User has specified priority for new policy"
        def priority = new Priority(100)


        and: "User has specified max allowed power usage for policy"
        def maxPowerUsage = 1500

        when: "User start process of adding new policy with given specified values"
        def policy = policyManagementUseCase.createPowerUsagePolicy(
                timePeriod,
                priority,
                maxPowerUsage
        )
        policyManagementUseCase.addPolicyToDevice(policyManagementUseCase.fetchDevice(deviceId).get(), policy)

        then: "System should contain new policy for device"
        policyManagementUseCase.fetchDevice(deviceId).isPresent()
        policyManagementUseCase.fetchDevice(deviceId).get().getPolicies()
                .stream()
                .filter { (it.getEffectiveDate() == timePeriod) }
                .filter { (it.getPriority() == priority) }
                .filter { (it.getMaxPowerUsageRule().getValue() == maxPowerUsage) }
                .findAny().isPresent()
    }

    def "User should be able to remove policy from device"() {
        //todo
    }

    def "User should be able to reschedule policy"() {
        //todo
    }
}
