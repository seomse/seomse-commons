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
아직은 규모가 크지 않아서 공통에 작업하고 있는 부분들은 내부 규모가 커지면 개별 artifact 로 분리될 수 있습니다.
 
# gradle
implementation 'com.seomse.commons:seomse-commons:1.2.3'
- etc 
    - https://mvnrepository.com/artifact/com.seomse.commons/seomse-commons/1.2.3


# communication
### blog, homepage
- [www.seomse.com](https://www.seomse.com/)
- [github.com/seomse](https://github.com/seomse)
- [seomse.tistory.com](https://seomse.tistory.com/)
- [seomse.github.io](https://seomse.github.io/)
- seomse.com

### 카카오톡 오픈톡
 - https://open.kakao.com/o/g6vzOKqb

### 슬랙 slack
- https://seomse.slack.com/

### email
 - comseomse@gmail.com
 
 
# main developer
 - macle
    -  [github.com/macle86](https://github.com/macle86)