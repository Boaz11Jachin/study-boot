package org.codenova.start.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codenova.start.model.TravelWarning;
import org.codenova.start.model.travel.TravelJSON;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TravelWarningAPIService {

    public TravelWarning[] findAll(int pageNo) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange("https://apis.data.go.kr/1262000/TravelWarningServiceV2/getTravelWarningListV2?serviceKey=Vw25fQSAsfNycj/AXwgHlM66HYmyfKPkX8pSs7dRqhRB1CqtZvhH0mUoAjue6h3CmrUQTjIBD3mHhflG7pedpA==&numOfRows=10&pageNo="+pageNo,
                HttpMethod.GET, null, String.class);


        String rawBody = response.getBody();
        //System.out.println(rawBody);



        ObjectMapper objectMapper = new ObjectMapper();


        // 변환과정없이 필요한 부분만 읽기
        JsonNode root = objectMapper.readTree(rawBody);
        String s1 = root.path("response").path("header").path("resultMsg").toString();
        System.out.println(s1);
        String s2 = root.path("response").path("body").path("items").path("item").toString();
        System.out.println(s2);

        TravelWarning[] travelWarnings =
                objectMapper.readValue(root.path("response").path("body").path("items").path("item").toString() , TravelWarning[].class);

        return travelWarnings;
    }

    public TravelWarning findByIsoCode(String isoCode) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://apis.data.go.kr/1262000/TravelWarningServiceV2/getTravelWarningListV2?serviceKey=Vw25fQSAsfNycj/AXwgHlM66HYmyfKPkX8pSs7dRqhRB1CqtZvhH0mUoAjue6h3CmrUQTjIBD3mHhflG7pedpA==&numOfRows=10&pageNo=1&cond[isoCode::EQ]="+isoCode,
                HttpMethod.GET,
                null,   // 그외 설정할 헤더값
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.getBody());
        TravelWarning warning = objectMapper.readValue(root.path("response").path("body").path("items").path("item").get(0).toString(),
                TravelWarning.class);
        // Item.class도 가능 (내거) 위는 강사님거

        return warning;
    }


}
