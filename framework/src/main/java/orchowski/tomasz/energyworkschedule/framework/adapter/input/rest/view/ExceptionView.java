package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view;

import lombok.Data;

import java.util.List;

@Data
public class ExceptionView {
    private final String title;
    private final List<String> details;
}
