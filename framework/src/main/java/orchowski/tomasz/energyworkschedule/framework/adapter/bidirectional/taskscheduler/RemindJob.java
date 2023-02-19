package orchowski.tomasz.energyworkschedule.framework.adapter.bidirectional.taskscheduler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.energyworkschedule.domain.event.ShiftChangeRemind;

@AllArgsConstructor
@Slf4j
@Getter
class RemindJob implements Runnable {
    private final ShiftChangeRemind shiftChangeRemind;

    @Override
    public void run() {
        log.info("Begins {} for device {}", shiftChangeRemind.getType(), shiftChangeRemind.getDeviceId());
    }
}
