package com.gdscGCC.ghostform.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.gdscGCC.ghostform.Config.ChatGPTConfig;
import com.gdscGCC.ghostform.Dto.ChatGPTRequestDto;
import com.gdscGCC.ghostform.Dto.ChatGPTResponseDto;
import com.gdscGCC.ghostform.Entity.ChatGPTMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/** ChatGPT와의 통신을 담당하는 service
 *  - Cakeblade
 *  */
@Service
@RequiredArgsConstructor
public class ChatGPTService {

    @Value(value = "${chatgpt.api_key}")
    private String api_key;
    private final RestTemplate restTemplate = new RestTemplate();

    /** https header를 만드는 method */
    public HttpEntity<ChatGPTRequestDto> buildHttpEntity(ChatGPTRequestDto chatGptRequest){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(ChatGPTConfig.MEDIA_TYPE));
        httpHeaders.add(ChatGPTConfig.AUTHORIZATION, ChatGPTConfig.BEARER + api_key);
        return new HttpEntity<>(chatGptRequest, httpHeaders);
    }

    /** Object Mapper */
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE );


    /** OpenAI API와 직접 통신하는 method */
    public ChatGPTResponseDto askQuestion(String questionRequest){

        List<ChatGPTMessage> messages = new ArrayList<>();
        messages.add(ChatGPTMessage.builder()
                .role(ChatGPTConfig.ROLE)
                .content(questionRequest)
                .build());
        return this.getChatGptResponse(
                this.buildHttpEntity(
                        new ChatGPTRequestDto(
                                ChatGPTConfig.CHAT_MODEL,
                                ChatGPTConfig.MAX_TOKEN,
                                ChatGPTConfig.TEMPERATURE,
                                ChatGPTConfig.STREAM,
                                messages
                                //ChatGptConfig.TOP_P
                        )
                )
        );

    }

    /** askQuestion에서 진행한 통신을 받아 dto 형태로 return하는 method */
    public ChatGPTResponseDto getChatGptResponse(HttpEntity<ChatGPTRequestDto> chatGptRequestHttpEntity)
    {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);
        requestFactory.setReadTimeout(60 * 1000);   //  1min = 60 sec * 1,000ms
        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<ChatGPTResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGPTConfig.CHAT_URL,
                chatGptRequestHttpEntity,
                ChatGPTResponseDto.class);

        return responseEntity.getBody();
    }

    /** ChaptGPT의 답변을 stream 형태로 받아오는 Method */
    public Flux<String> getChatGptStreamResponse(String question) throws JsonProcessingException
    {
        WebClient client = WebClient.builder()
                .baseUrl(ChatGPTConfig.CHAT_URL)
                .defaultHeader(
                        HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .defaultHeader(
                        ChatGPTConfig.AUTHORIZATION,
                        ChatGPTConfig.BEARER + api_key)
                .build();

        List<ChatGPTMessage> messages = new ArrayList<>();
        messages.add(ChatGPTMessage.builder()
                .role(ChatGPTConfig.ROLE)
                .content(question)
                .build());

        ChatGPTRequestDto chatGptRequest = new ChatGPTRequestDto(
                ChatGPTConfig.CHAT_MODEL,
                ChatGPTConfig.MAX_TOKEN,
                ChatGPTConfig.TEMPERATURE,
                ChatGPTConfig.STREAM,
                messages
                // ChatGPTConfig.TOP_P
        );
        String requestValue = objectMapper.writeValueAsString(chatGptRequest);

        return client.post()
                .bodyValue(requestValue)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class);
    }

}
