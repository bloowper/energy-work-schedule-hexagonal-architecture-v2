package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPowerUsagePolicyRestRequest {
    Instant start;
    Instant end;
    int priority;
    Double maxAllowedPowerUsage;
}
