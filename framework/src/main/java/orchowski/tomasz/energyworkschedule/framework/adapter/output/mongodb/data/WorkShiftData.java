package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkShiftData {
    Instant start;
    Instant end;
    MaxPowerUsageRuleData rule;
}
