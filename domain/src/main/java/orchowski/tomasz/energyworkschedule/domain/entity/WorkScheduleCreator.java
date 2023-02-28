package orchowski.tomasz.energyworkschedule.domain.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.domain.value.TimelinePoint;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

import static orchowski.tomasz.energyworkschedule.domain.value.TimelinePoint.Type.END;
import static orchowski.tomasz.energyworkschedule.domain.value.TimelinePoint.Type.START;


@RequiredArgsConstructor(staticName = "of")
@Slf4j
class WorkScheduleCreator {
    // [Q] shouldn't be in service package?
    // from my perspective this is part of Device aggregate behaviour but moved it to separate class for readability’s sake
    private final SortedSet<Policy> policies;

    public WorkSchedule createWorkSchedule() {
        // TODO REFACTOR!
        List<TimelinePoint> timelinePoints = mapToSortedTimeLinePoints(policies);
        log.debug("Starting creating work schedule from time line points {}", timelinePoints);
        List<WorkShift> workShifts = createSortedWorkShifts(timelinePoints);
        return new WorkSchedule(
                new TimePeriod(
                        workShifts.get(0).getDuration().getStart(),
                        workShifts.get(workShifts.size() - 1).getDuration().getEnd()
                ),
                workShifts
        );
    }

    private List<TimelinePoint> mapToSortedTimeLinePoints(SortedSet<Policy> policies) {
        return policies.stream()
                .flatMap(policy ->
                        List.of(
                                new TimelinePoint(policy.getEffectiveDate().getStart(), START, policy),
                                new TimelinePoint(policy.getEffectiveDate().getEnd(), END, policy)
                        ).stream()
                ).sorted(Comparator.comparing(TimelinePoint::getInstant))
                .toList();
    }

    private List<WorkShift> createSortedWorkShifts(List<TimelinePoint> timelinePoints) {
        List<WorkShift> workShifts = new ArrayList<>(timelinePoints.size() - 1);
        WorkShift.WorkShiftBuilder workShiftBuilder = WorkShift.builder();
        TimelinePoint previousPoint = null;
        for (TimelinePoint point : timelinePoints) {
            if (previousPoint == null || point.getType().equals(START) && previousPoint.getType().equals(END)) {
                workShiftBuilder.start(point.getInstant());
                workShiftBuilder.rule(point.getOriginPolicy().getMaxPowerUsageRule());
                previousPoint = point;
            } else if (point.getType().equals(START) && point.getOriginPolicy().getPriority().getValue() > previousPoint.getOriginPolicy().getPriority().getValue()) {
                workShiftBuilder.end(point.getInstant());
                workShifts.add(workShiftBuilder.build());
                workShiftBuilder.start(point.getInstant());
                workShiftBuilder.rule(point.getOriginPolicy().getMaxPowerUsageRule());
                previousPoint = point;
            } else if (point.getType().equals(END) && point.getOriginPolicy() == previousPoint.getOriginPolicy()) {
                workShiftBuilder.end(point.getInstant());
                workShifts.add(workShiftBuilder.build());
                previousPoint = point;
            } else if (point.getType().equals(END) && previousPoint.getType().equals(END)) {
                workShiftBuilder.end(point.getInstant());
                workShiftBuilder.rule(point.getOriginPolicy().getMaxPowerUsageRule());
                workShiftBuilder.start(previousPoint.getInstant());
                workShifts.add(workShiftBuilder.build());
                previousPoint = point;
            }
        }
        return workShifts;
    }

}
