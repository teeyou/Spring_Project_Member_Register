# 스프링 회원가입 학습 필기
- 유튜브 강의 - https://www.youtube.com/playlist?list=PLV9zd3otBRt7HQxBTdpJ_85UEvKJl2mJ0
- Java 버전과 pom에 등록한 라이브러리의 버전이 맞지않으면 에러가 발생. 라이브러리의 버전을 낮춰서 해결함.

- main > java 디렉토리 만들고 여기에서 java파일 생성

- main > resources   
mybatis-config.xml 파일에 별칭을 정의해두면   
mapper > memberMapper.xml 파일에 parameterType을 별칭으로 쓸 수 있음   
별칭을 정의하지않으면 parameterType에 DTO 클래스가 있는 전체경로를 써줘야함

- webapp > WEB-INF   
여기에 root-context.xml , servlet-context.xml , web.xml 파일생성함   
context 파일이 제대로 되어있지 않으면 java 디렉토리 안에 있는 자바 클래스파일이 정상적으로 실행안됨

- webapp > WEB-INF > views
여기에 jsp파일 생성함   
servlet-context.xml로 인해서 controller와 views에 있는 jsp파일들이 제대로 매핑되어서 동작함

- DTO 또는 VO - 차이는 있지만 혼용해서 쓰긴함

- Model 객체 - Controller에서 사용하는 데이터를 View에 전달할 때 사용
- @ModelAttribute - 파라미터로 들어오는 값들(Form에서 보내는 값)을 받아서 객체을 생성하고 객체에 set하는 코드를 생략해서 처리해줌

- @RequestParam - 쿼리스트링 파라미터 받아올 때 사용

- @ResponseBody - ajax 응답을 처리할 때 @ResponseBody를 써야함 (브라우저 리프레쉬없이 서버와 브라우저간의 통신을 통해서 이메일 중복체크시 사용함)

- redirect - Controller에서 redirect할 때는 jsp파일명이 아닌 경로명을 써줘야함

## MyBatis
- MyBatis는 SQL을 직접 작성해야함 (JPA는 SQL을 작성할 필요없음)
- mybatis를 이용할 때, DB의 컬럼명과 DTO의 필드명이 일치하지않으면 에러발생
이름을 일치시키거나 snake_case를 camelCase로 자동변환하는 등 설정을 mybatis-config.xml에서 해주면 됨

## Controller, Service, Repository
- Controller - 요청이 들어오면 실행되는 시작점이고, 어떤 경로에 어떤 메소드 방식으로 요청이 들어오는지 정의하고 비지니스 로직은 Service에 넘김

- Service - 요청에 대한 비지니스 로직들을 여기에서 수행. DB에 접근해서 데이터를 받아오는 로직은 Repository에 넘김

- Repository - DAO의 역할을 함. 여기서 DB를 조작하는 코드를 작성
