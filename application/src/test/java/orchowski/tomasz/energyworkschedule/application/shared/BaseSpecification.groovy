package orchowski.tomasz.energyworkschedule.application.shared

import orchowski.tomasz.energyworkschedule.application.port.input.DeviceManagementInputPort
import orchowski.tomasz.energyworkschedule.application.port.input.PolicyManagementInputPort
import spock.lang.Specification

class BaseSpecification extends Specification {
    protected def deviceManagementOutputPort = new DeviceManagementStubOutputAdapter()
    protected def workScheduleSnapshotOutputPort = new WorkScheduleSnapshotStubOutputAdapter()
    protected def scheduleShiftChangeRemindOutputPort = new ScheduleShiftChangeRemindStubOutputPort();

    protected def deviceManagementUseCase = new DeviceManagementInputPort(deviceManagementOutputPort, workScheduleSnapshotOutputPort, scheduleShiftChangeRemindOutputPort)
    protected def policyManagementUseCase = new PolicyManagementInputPort(deviceManagementOutputPort, workScheduleSnapshotOutputPort, scheduleShiftChangeRemindOutputPort)

}
