package team5.team5server.domain.user.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team5.team5server.global.response.BaseResponse;

@RestController
public class HealthController {

    @GetMapping("/health")
    public BaseResponse<String> health() {
        return BaseResponse.ok("hello");
    }
}
