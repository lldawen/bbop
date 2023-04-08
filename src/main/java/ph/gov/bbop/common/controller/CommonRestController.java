package ph.gov.bbop.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ph.gov.bbop.common.dto.Message;
import ph.gov.bbop.common.dto.MessageWithData;

@RestController
@Slf4j
public class CommonRestController {

    protected ResponseEntity<Message> message(Object data) {
        log.info("CommonRestController | responseEntityWithDetails | Start");
        MessageWithData messageWithData = new MessageWithData(data);
        return ResponseEntity.ok(messageWithData);
    }

    protected ResponseEntity<Message> messageWithHeaders(Object data, HttpHeaders httpHeaders) {
        MessageWithData messageWithData = new MessageWithData(data);
        return ResponseEntity.ok().headers(httpHeaders).body(messageWithData);
    }

    @ExceptionHandler
    protected ProblemDetail handleException(Throwable throwable) {
        log.error("CommonRestController | responseEntityWithDetails | Error: ", throwable);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
    }

}
