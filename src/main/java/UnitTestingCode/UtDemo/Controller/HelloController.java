package UnitTestingCode.UtDemo.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    //application.properties file configuration
//    @Value("${welcome.message}")
//    private String welcomeMessage;
//
//    @GetMapping("/")
//    public String getData()
//    {
//        return welcomeMessage;
//    }
}
