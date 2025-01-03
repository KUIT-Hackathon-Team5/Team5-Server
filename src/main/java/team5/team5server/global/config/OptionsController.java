package team5.team5server.global.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import team5.team5server.global.response.BaseResponse;

@RestController
public class OptionsController {

    @RequestMapping(method = RequestMethod.OPTIONS, path = "/**")
    public BaseResponse<Void> handleOptions() {
        return BaseResponse.ok(null);
    }
}
