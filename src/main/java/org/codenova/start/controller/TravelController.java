package org.codenova.start.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codenova.start.model.travel.TravelJSON;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/travel")
public class TravelController {

    @GetMapping("/warning")
    public String warningHandle(Model model) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange("https://apis.data.go.kr/1262000/TravelWarningServiceV2/getTravelWarningListV2?serviceKey=bGRymAZvyM42LFSX1PliPIXC7%2Fwsx8NT1cWoIDegoDmhVXnCYh%2Fedvs7I9%2BtRjsosiyHAgg3F4eir%2BIKkIsvZg%3D%3D&pageNo=1&numOfRows=200",
        HttpMethod.GET, null, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        TravelJSON travelJSON = objectMapper.readValue(response.getBody(), TravelJSON.class);
        model.addAttribute("body", travelJSON.getResponse().getBody());

        travelJSON.getResponse().getBody().getItems().getItem().get(0).getCountryName();


        return "travel/warning";
    }
}
