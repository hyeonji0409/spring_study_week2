# spring_study_week2

+ API를 어떻게 실용적으로 만들까?
+ 간단한 CRUD Api 만들기

## Spring Web 진입점 생성
+ Controller 객체 생성 -> client의 요청을 받는다.
+ ping/pong api 구현
+ @GetMapping("/")에서 뒤의 괄호는 생략 가능함

## API 구현
+ API는 응답코드와 응답 설명이 필요하다.
++ 운영하는 서비스에서 오류가 발생할 경우 이유가 무엇인지 확인하기 위함

+ controller/PingController.java
```
@RestController
public class PingController {
    @GetMapping
    public Object ping() {
        return Map.of(
                "code", "0000",
                "desc", "정상입니다",
                "data", "pong"
        );
    }
}
```
+ Map은 사실상 익명 객체라고 생각. 다른사람과 ㄱ옹하면 응답을 만든건지 가독성이 떨어지기 때문에 클래스로 묶어 응답객체라고 묶어주는 것이 효율적

+ dto/Response.java
```
public class Response {
  private String code;
  private String desc;
  private String data;

  public Response(String code, String desc, String data) {
    this.code = code;
    this.desc = desc;
    this.data = data;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }
}
```
+ controller/PingController.java
```
@RestController
public class PingController {
    @GetMapping
    public Object ping() {
        return new Response("0000", "정상입니다", "pong");
    }
}
```
+ API 응답구조를 만드는 코드가 중복 => 개선 필요
+ code, desc 각각의 결합도가 높은데 둘을 묶어주지 않았기 때문에 중복의 이슈가 발생한다.

## lombok
+ 보일러플레이트 코드의 증가 -> lombok으로 개선
+ 의존성 추가
```
dependencies {
	compileOnly 'org.projectlombok:lombok:1.18.20'
	annotationProcessor 'org.projectlombok:lombok:1.18.20'
}
```

## 동적 타입의 활용
+ Response 객체의 구조에서 data는 항상 String 타입을 받는다. => 항상 String 구조를 가져야 함
+ 제네릭 문법을 통해 개선
```
@Getter
@Builder
public class Response<T> {
    private final ApiCode code;
    private final T data;
}
```
## ResponseEntity 객체 활용하기
```
@RestController
public class PingController {
  @GetMapping
  public ResponseEntity<Response<String>> ping() {
    Response<String> response = Response.<String>builder()
            .code(ApiCode.SUCCESS).data("pong").build();

    return ResponseEntity
            .status(HttpStatus.OK)
            .body(response);
  }
} 
```
## REST API
+ HTTP method르 행위 표현
++ @GetMapping: 조회
++ @PostMapping: 생성
++ @PutMapping: 변경
++ @DeleteMapping: 삭제

+ REST 구조의 장점
++ 클라이언트를 위한 API가 아닌 비즈니스 도메인을 표현하는 API임.
++ Resource / domain을 중심으로 표현

## Article 객체 생성

## ArticleService 생성 & save 함수 구현
+ Service => 실제 비즈니스 로직을 처리하기 위함
+ 같은 Service를 각각의 controller에서 접근 가능할 수 있도록 구분

## ArticleController 생성 및 ArticleService 생성자 주입
```
@RequiredArgsConstructor
@RequestMapping("/api/v1/article")
@RestController
public class ArticleController {
    private final ArticleService articleService;
}
```

## Article DTO(Data Transfer Object) 객체 생성
+ domain영역의 객체와 Presentation 영역의 객체 구분되어야 함

## DTO란?
+ 데이터를 오브젝트로 변환하는 객체(단, 주체가 누구인가를 아는 것이 가장 중요)
+ 계층간 데이터 교환을 위해 사용
