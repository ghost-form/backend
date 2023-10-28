package com.gdscGCC.ghostform.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gdscGCC.ghostform.Dto.ChatGPT.ChatGPTResponseDto;
import com.gdscGCC.ghostform.Service.ChatGPTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/chatgpt")
public class ChatGPTAPIController {

    private final ChatGPTService chatgptService;

    /** 1개의 답변으로 오는 API */
    @PostMapping("/chat")
    public ChatGPTResponseDto chat(@RequestBody String question, Locale locale, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
        ChatGPTResponseDto chatGptResponse = null;
        try {
            chatGptResponse = chatgptService.askQuestion(question);
        } catch (Exception e) {
            log.warn(e.toString());
        }

        return chatGptResponse;
    }

    /** Stream 형태로 질답을 진행하는 API */
    @PostMapping(value = "/chatStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatStream(@RequestBody String question, Locale locale, HttpServletRequest request, HttpServletResponse response) {
        try {
            return chatgptService.getChatGptStreamResponse(question);
        } catch (JsonProcessingException e) {
            log.warn(e.toString());
            return Flux.empty();
        }
    }

}
