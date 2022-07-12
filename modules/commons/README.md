# seomse-commons

# 개발환경
-   open jdk 1.8

# 구성
### 연관 프로젝트에서 활용 하는 유틸성 클래스
 - 문자열
 - 시간
 - 파일
 - 기타 공통 으로 사용될 만한 유틸성 클래스
 
### 설정 관리, 설정 우선 순위 관리
- 설정의 우선수위가 필요한 경우에 우선순위를 판단해서 설정을 관리하는 기능을 제공
  (같은 설정이 있으면 우선순위가 높은곳에서 가져오고, 설정이 없는 값만 우선순위가 낮은곳에서 가져 옴)
- xml 파일
- database
- system properties
- 등 다양한 조건의 설정을 우선순위를 관리해서 사용가능
 
### 서비스 관리
 - 서비스로 실행되는 Thread 관리기능 제공
 
### 공통에 많은 기능이 있는 문제
- 아직은 규모가 크지 않아서 공통에 작업하고 있는 부분들은 내부 규모가 커지면 개별 artifact 로 분리될 수 있습니다.
- 너무 많은 부분이 공통이 모이는 문제는 많이 경계 하고 있습니다.. 아무래도 데이터 처리 관련 일을 많이 하다 보니 관련 부분에 대한 공통이 많이질 것 같네요.. 
# gradle
implementation 'com.seomse.commons:commons:1.3.2'
- etc 
    - https://mvnrepository.com/artifact/com.seomse.commons/commons/1.3.2

# post
- [java 파일 라인수 얻기, 특정라인 빨리 읽기 (빠른 라인처리)](https://macle.dev/posts/java_file_fast_read_line/)
# communication
### blog, homepage
- [www.seomse.com](https://www.seomse.com/)
- [runon.io](https://runon.io)
- [github.com/seomse](https://github.com/seomse)
- [github.com/runonio](https://github.com/runonio)

### 카카오톡 오픈톡
 - https://open.kakao.com/o/g6vzOKqb
    - 참여코드: runon 

### 슬랙 slack
- https://seomse.slack.com/

### email
 - comseomse@gmail.com
 
 
# main developer
 - macle
    -  [github.com/macle86](https://github.com/macle86)
