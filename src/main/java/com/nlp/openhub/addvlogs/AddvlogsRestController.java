package com.nlp.openhub.addvlogs;

import com.nlp.openhub.classes.ApiCheckVlog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AddvlogsRestController {
    public ApiCheckVlog getFlaskClient(String vlogText) {
        String url = "http://127.0.0.1:5000/vlog/" + vlogText;
        RestTemplate restTemplate = new RestTemplate();
        ApiCheckVlog result = restTemplate.getForObject(url, ApiCheckVlog.class);
        return result;
    }

}
