package ph.gov.bbop.common.util;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

@Component
@Slf4j
public class EmailSenderAPI {

    private final JavaMailSender emailSender;

    public EmailSenderAPI(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void send(Map<String, String> emailParameters) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper msgHelper = null;
        try {
            msgHelper = new MimeMessageHelper(message, true);
            msgHelper.setTo(emailParameters.get("toEmail"));
            msgHelper.setSubject(emailParameters.get("subject"));
            msgHelper.setText(emailParameters.get("body"));

            File attachment = new File(emailParameters.get("certificate"));
            FileSystemResource certificateAttachment = new FileSystemResource(attachment);
            msgHelper.addAttachment(FilenameUtils.getName(attachment.getAbsolutePath()), certificateAttachment);
            emailSender.send(message);
        } catch (Exception e) {
            log.error("EmailSenderAPI | send | error: {}", e.getMessage(), e);
        }
    }
}
