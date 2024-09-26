//package com.Twilio.controller;
//
//
//import com.Twilio.service.TwilioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/events")
//public class TwilioController {
//
//
//    @Autowired
//    private EventService eventService;
//
//    @Autowired
//    private TwilioService twilioService;
//
//    @PostMapping("/{eventId}/send-sms")
//    public String sendSmsToGuests(@PathVariable Long eventId, @RequestParam String message) {
//        try {
//            eventService.sendSmsToEmergencyContact(eventId, message);
//            return "Messages sent successfully";
//        } catch (Exception e) {
//            return "Failed to send messages: " + e.getMessage();
//        }
//    }
//}

package com.Twilio.controller;

import com.Twilio.service.TwilioService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sos")
public class SosController {

    @Autowired
    private TwilioService twilioService;

    @PostMapping("/send-alert/{userId}")
    public String sendSosAlert(@PathVariable String userId, @RequestBody SosDetails sosDetails) {
        twilioService.sendSmsToEmergencyContact(userId, sosDetails.getEmergencyType() + " at " + sosDetails.getAddress());
        return "SOS Alert Sent!";
    }
}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class SosDetails {
    private String emergencyType;
    private String lat;
    private String lon;

    // Getters and Setters
}
