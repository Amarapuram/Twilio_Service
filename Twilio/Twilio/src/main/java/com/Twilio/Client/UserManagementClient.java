package com.Twilio.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-management-service", url = "${user.management.service.url}")
public interface UserManagementClient {

    @GetMapping("/users/{userId}/emergency-contacts")
    EmergencyContactResponse getEmergencyContact(@PathVariable("userId") String userId);
}

class EmergencyContactResponse {
    private String name;
    private List<String> phoneNumbers;

    // Getters and Setters
}
