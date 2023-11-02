package com.gdscGCC.ghostform.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdscGCC.ghostform.Dto.AskRequestDto;
import com.gdscGCC.ghostform.Dto.ChatGPTResponseDto;
import com.gdscGCC.ghostform.Dto.TemplateRequestDto;
import com.gdscGCC.ghostform.Dto.TemplateResponseDto;
import com.gdscGCC.ghostform.Entity.Ask;
import com.gdscGCC.ghostform.Entity.Template;
import com.gdscGCC.ghostform.Service.ChatGPTService;
import com.gdscGCC.ghostform.Service.TemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Iterator;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/templates")
@Tag(name = "Template", description = "Template API")
public class TemplateAPIController {
    private final TemplateService templateService;
    private final ChatGPTService chatGPTService;

    @GetMapping("/{id}")
    public void templateGet(@PathVariable Long id){
        templateService.findById(id);
    }

    @PostMapping("/save")
    public Long templateSave(@RequestBody TemplateRequestDto requestDto){
        return templateService.save(requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public void templateDelete(@PathVariable Long id){
        templateService.delete(id);
    }

    @PutMapping("/{id}")
    public Long templateUpdate(@PathVariable Long id, @RequestBody TemplateRequestDto requestDto){
        System.out.println("id는!!! : " + id);
        return templateService.update(id, requestDto);
    }

    @PostMapping(value = "/{id}/ask", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> makeQuestionStream (@PathVariable Long id, @RequestBody AskRequestDto askRequestDto) {
        TemplateResponseDto template = templateService.findById(id);
        String question = template.getContent();
        for (Ask a : askRequestDto.getAskList()) {
            question = question.replace("{{" + a.getKey() + "}}", a.getValue());
        }
        try {
            return chatGPTService.getChatGptStreamResponse(question);
        } catch (JsonProcessingException e) {
            log.warn(e.toString());
            return Flux.empty();
        }
    }

    @PostMapping(value = "/{id}/ask/json")
    public ResponseEntity<ChatGPTResponseDto> makeQuestion (@PathVariable Long id, @RequestBody AskRequestDto askRequestDto) {
        TemplateResponseDto template = templateService.findById(id);
        String question = template.getContent();
        for (Ask a : askRequestDto.getAskList()) {
            question = question.replace("{{" + a.getKey() + "}}", a.getValue());
        }
        ChatGPTResponseDto chatGPTResponseDto = null;
        try {
            chatGPTResponseDto = chatGPTService.askQuestion(question);
        } catch (Exception e) {
            log.warn(e.toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body(chatGPTResponseDto);
    }
}
