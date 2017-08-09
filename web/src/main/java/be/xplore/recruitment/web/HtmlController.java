package be.xplore.recruitment.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lander
 * @since 28/07/2017
 */
@Controller
public class HtmlController {

    @RequestMapping("/")
    public String greeting() {
        return "index.html";
    }


}
