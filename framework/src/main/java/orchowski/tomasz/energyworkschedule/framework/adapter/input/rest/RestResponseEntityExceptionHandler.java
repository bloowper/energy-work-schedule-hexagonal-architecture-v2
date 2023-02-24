package orchowski.tomasz.energyworkschedule.framework.adapter.input.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orchowski.tomasz.energyworkschedule.domain.exception.GenericSpecificationException;
import orchowski.tomasz.energyworkschedule.framework.adapter.input.rest.view.ExceptionView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;


@ControllerAdvice(assignableTypes = {DeviceManagementInputAdapter.class, PolicyManagementInputAdapter.class, WorkScheduleViewInputAdapter.class})
@Slf4j
@RequiredArgsConstructor
class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {GenericSpecificationException.class})
    ResponseEntity<ExceptionView> handleException(RuntimeException ex) {
        return new ResponseEntity<>(
                new ExceptionView("SpecificationException", List.of(ex.getMessage())),
                HttpStatus.BAD_REQUEST
        );
    }

}
