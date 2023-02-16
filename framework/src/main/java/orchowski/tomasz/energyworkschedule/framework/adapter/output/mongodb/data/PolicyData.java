package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PolicyData {
    String id;
    Instant start;
    Instant stop;
    Integer priority;
    MaxPowerUsageRuleData rule;
}
