package br.gov.al.detran.detranfp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zanutto on 2/23/15.
 */
@Controller
public class HomeController {

	@Autowired
	Environment env;
	
    @RequestMapping(value = {"/", "/index"})
    public String index (Map<String, Object> model) {
    	
    	model.put("environment", env.getActiveProfiles()[0]);
    	
    	return "index";
    	
    }
}
