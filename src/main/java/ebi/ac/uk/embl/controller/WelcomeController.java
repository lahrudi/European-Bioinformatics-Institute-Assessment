package ebi.ac.uk.embl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alireza Gholamzadeh Lahroodi on 2/13/2021.
 */
@RestController
public class WelcomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);

    /**
     * <p>Welcome page for the main root</p>
     */
    @RequestMapping(value = "/public", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String welcome() {
        return "<html><body><div>REST API for European Bioinformatics Institute</div></body></html>";
    }
}
