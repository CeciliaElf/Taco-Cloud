package cecilia.tacocloud.tacos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author cecilia
 * 主頁面控制器
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
