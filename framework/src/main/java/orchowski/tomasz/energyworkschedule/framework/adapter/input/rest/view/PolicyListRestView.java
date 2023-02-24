package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PolicyListRestView {
    private List<PolicyRestView> policies;

}
