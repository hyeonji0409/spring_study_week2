package com.artineer.spring_study_week2.dto;

import com.artineer.spring_study_week2.vo.ApiCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Response<T> {
    private ApiCode code;
    private T data;
}
/*
    private Response() {}

    public Response(ApiCode code, String data) {
        this.code = code;
        this.data = data;
    }

    lombok 의 @Getter 를 제공받아 getter 함수 필요 없어짐

    @Builder 제공받아 필요 없어짐
    //Builder 함수 호출
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Response response;

        public Builder() {
            this.response = new Response();
        }

        public Builder code(ApiCode code) {
            this.response.code = code;
            return this;
        }

        public Builder data(String data) {
            this.response.data = data;
            return this;
        }

        public Response build() {
            return this.response;
        }
    }
    */
