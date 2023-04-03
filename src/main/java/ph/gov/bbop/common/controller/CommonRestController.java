package ph.gov.bbop.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ph.gov.bbop.common.dto.KeyValue;
import ph.gov.bbop.common.dto.MessageDetail;

import java.util.Arrays;

@RestController
@Slf4j
public class CommonRestController {

    protected ResponseEntity<MessageDetail> responseEntityWithDetails(Object responseObject) {
        log.info("CommonRestController | responseEntityWithDetails | Start");
        MessageDetail messageDetail = new MessageDetail();
        messageDetail.setSuccess(true);
        messageDetail.setMessage("Success");
        messageDetail.setData(responseObject);
        log.info("CommonRestController | responseEntityWithDetails | messageDetail: " + messageDetail);
        return ResponseEntity.ok(messageDetail);
    }

    @ExceptionHandler
    protected ProblemDetail handleException(Throwable throwable) {
        log.error("CommonRestController | responseEntityWithDetails | Error: ", throwable);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, throwable.getMessage());
    }

}
