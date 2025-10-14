package com.ilouse.notification;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${twilio.phoneNumber}")
    private String fromPhoneNumber;

    @Override
    @KafkaListener(topics = "customer-event", groupId = "notification-group")
    public void consume(String message) {
        sendSms("+212625911500", message);
    }

    @Override
    public void sendSms(String toPhoneNumber, String message) {
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), message).create();
    }
}