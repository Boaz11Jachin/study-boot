package org.codenova.start.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.codenova.start.entity.Comment;
import org.codenova.start.model.TravelWarning;
import org.codenova.start.model.travel.TravelJSON;
import org.codenova.start.repository.CommentRepository;
import org.codenova.start.service.TravelWarningAPIService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/travel")
public class TravelController {

    private TravelWarningAPIService travelWarningAPIService;
    private CommentRepository commentRepository;

    @GetMapping("/warning")
    public String warningHandle(@RequestParam("p") Optional<Integer> p, Model model) throws JsonProcessingException {

        int pValue = p.orElse(1);


//        TravelJSON travelJSON = objectMapper.readValue(response.getBody(), TravelJSON.class);
//        model.addAttribute("body", travelJSON.getResponse().getBody());
//        String s = travelJSON.getResponse().getBody().getItems().getItem().get(0).getCountryName();
//        System.out.println(s);
//        // JSON 에서 변환하기

        TravelWarning[] warnings = travelWarningAPIService.findAll(pValue);


        model.addAttribute("travelWarnings", warnings);
        return "travel/warning";
    }

    @GetMapping("/warning/detail")
    public String warningDetailHandle(@RequestParam("isoCode") String isoCode, Model model) throws JsonProcessingException {
        System.out.println("param = " +isoCode);

        TravelWarning warning =
                travelWarningAPIService.findByIsoCode(isoCode);


        model.addAttribute("data", warning);

        model.addAttribute("count", commentRepository.countByIsoCode(isoCode));

        List<Comment> comments = commentRepository.findByIsoCode(isoCode);
        model.addAttribute("comments", comments);


        return "travel/warning/detail";
    }

    @PostMapping("/warning/comments")
    public String commentsHandle(@ModelAttribute Comment comment){

        commentRepository.create(comment);

        return "redirect:/travel/warning/detail?isoCode="+comment.getIsoCode();
    }


}
