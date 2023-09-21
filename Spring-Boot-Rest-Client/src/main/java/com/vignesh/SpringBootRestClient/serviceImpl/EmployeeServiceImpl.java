package com.vignesh.SpringBootRestClient.serviceImpl;

import com.vignesh.SpringBootRestClient.dto.UserDetails;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class EmployeeServiceImpl {

    private final RestClient restClient;

    public EmployeeServiceImpl(){
        restClient= RestClient.builder()
                .baseUrl("http://localhost:8080/api/v1/logic/")
                .build();
    }

    public UserDetails saveUserDetails(UserDetails employee) throws Exception {

            return restClient.post()
                    .uri("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(employee)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,((request, response) -> {
                        throw new RuntimeException(response.getStatusText());
                    }))
                    .body(UserDetails.class);

    }


    public List<UserDetails> getAllUserDetails(){
        return restClient.get()
                .uri("/user")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public String deleteUserById(long id){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/user").queryParam("id",id);
        String uri = builder.toUriString();
        return restClient.delete()
                .uri(builder.toUriString())
                .retrieve()
                .body(String.class);
    }
}
