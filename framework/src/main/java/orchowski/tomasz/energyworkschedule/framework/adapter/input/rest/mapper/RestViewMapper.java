package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.mapper;

import orchowski.tomasz.energyworkschedule.domain.entity.Device;
import orchowski.tomasz.energyworkschedule.domain.entity.Policy;
import orchowski.tomasz.energyworkschedule.domain.value.Id;
import orchowski.tomasz.energyworkschedule.domain.value.MaxPowerUsageRule;
import orchowski.tomasz.energyworkschedule.domain.value.TimePeriod;
import orchowski.tomasz.energyworkschedule.domain.value.WorkSchedule;
import orchowski.tomasz.energyworkschedule.domain.value.WorkShift;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.DeviceRestView;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.PolicyListRestView;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.PolicyRestView;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.RuleRestView;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.TimePeriodRestView;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.WorkScheduleRestView;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.WorkShiftRestView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestViewMapper {
    public DeviceRestView toView(Device device) {
        return new DeviceRestView(toView(device.getId()), device.getPoliciesView().size());
    }


    public String toView(Id id) {
        return id.getUuid().toString();
    }

    public PolicyRestView toView(Policy policy) {
        return new PolicyRestView(
                toView(policy.getId()),
                toView(policy.getEffectiveDate()),
                policy.getPriority().getValue(),
                toView(policy.getMaxPowerUsageRule())
        );
    }

    public WorkScheduleRestView toView(WorkSchedule workSchedule) {
        return new WorkScheduleRestView(
                toView(workSchedule.getDuration()),
                workSchedule.getWorkShifts().stream().map(this::toView).toList()
        );
    }

    public PolicyListRestView toView(List<Policy> devicePolicies) {
        List<PolicyRestView> policyRestViews = devicePolicies.stream()
                .map(this::toView)
                .toList();
        return new PolicyListRestView(policyRestViews);
    }

    private RuleRestView toView(MaxPowerUsageRule rule) {
        return new RuleRestView(rule.getValue());
    }

    private TimePeriodRestView toView(TimePeriod timePeriod) {
        return new TimePeriodRestView(timePeriod.getStart(), timePeriod.getEnd());
    }

    private WorkShiftRestView toView(WorkShift workShift) {
        return new WorkShiftRestView(
                toView(workShift.getDuration()),
                toView(workShift.getRule())
        );
    }

}
