package com.ilouse.notification;

public interface NotificationService {
    void consume(String message);
    void sendSms(String toPhoneNumber, String message);
}
