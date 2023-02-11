package orchowski.tomasz.energyworkschedule.framework.adapter.output.mongodb.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class DeviceData {
    //[Q]What to do when we introduce a new policy that contradicts current ones.
    // How to leave then the possibility to restore the old domain objects from the database?
    @Id
    private UUID id;
    private List<PolicyData> policies;
}
