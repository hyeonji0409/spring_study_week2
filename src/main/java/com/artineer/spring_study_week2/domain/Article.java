package com.artineer.spring_study_week2.domain;

import lombok.Builder;
import lombok.Getter;

//실제 api로 내려가려면 표현의 영역을 거쳐 내려가야 한다 -> dto를 거침
@Getter
@Builder
public class Article {
    Long id;
    String title;
    String content;
}
