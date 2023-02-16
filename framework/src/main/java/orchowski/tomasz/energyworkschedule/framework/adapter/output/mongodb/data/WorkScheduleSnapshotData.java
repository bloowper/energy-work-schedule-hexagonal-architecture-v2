package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkScheduleSnapshotData {
    @Id
    String deviceId;
    Instant start;
    Instant end;
    List<WorkShiftData> workShifts;

}
