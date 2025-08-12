package com.ilouse.Notification;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfiguration {

    public NotificationConfiguration(
            @Value("${twilio.accountSid}") String accountSid,
            @Value("${twilio.authToken}") String authToken) {
        Twilio.init(accountSid, authToken);
    }
}
