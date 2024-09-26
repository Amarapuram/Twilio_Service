//package com.Twilio.service;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TwilioService {
//
//    @Value("${twilio.account-sid}")
//    private String accountSid;
//
//    @Value("${twilio.auth-token}")
//    private String authToken;
//
//
//    @Value("${twilio.phone-number}")
//    private String twilioPhoneNumber;
//
//    public TwilioService() {
//        String accountSid = "AC59a861a0e0f18378476ce10b5c5faad7";
//        String authToken = "b3a4ab856e7fc06b0633ec4b4d613b7f";
//
//        Twilio.init(accountSid, authToken);
//    }
//
//    public void sendSms(String toPhoneNumber, String messageBody) {
//        try {
//            Message message = Message.creator(
//                    new PhoneNumber(toPhoneNumber),
//                    new PhoneNumber(twilioPhoneNumber),
//                    messageBody
//            ).create();
//            System.out.println("Message sent successfully with SID: " + message.getSid());
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Failed to send message.");
//        }
//    }
//}
//
//
package com.Twilio.service;

import com.Twilio.Client.EmergencyContactResponse;
import com.Twilio.client.UserManagementClient;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    @Autowired
    private UserManagementClient userManagementClient;

    public TwilioService() {
        Twilio.init(accountSid, authToken);
    }

    public void sendSmsToEmergencyContact(String userId, String sosDetails) {
        try {
//            EmergencyContactResponse contactResponse = userManagementClient.getEmergencyContact(userId);
            String toPhoneNumber = contactResponse.getPhoneNumbers().get(0); // Get the first number

            String messageBody = String.format("SOS Alert! %s\nDetails: %s", contactResponse.getName(), sosDetails);
            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    messageBody
            ).create();
            System.out.println("Message sent successfully with SID: " + message.getSid());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to send message.");
        }
    }
}
