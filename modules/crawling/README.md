# seomse-crawling

# 개발환경
- open jdk 1.8

# 개요
데이터 수집을 위해 개발 되었습니다.

크롤링 노드관리가 Selenium, chrome 을 활용한 proxy node 관리는 다음 버전에 개발 될 예정입니다.

지금은 HttpURLConnection 을 활용한 crawling node 관리만 제공 합니다.

# 구성
- crawling proxy node 들을 관리 기능
- 재연결 대기 시간 기능
- HttpURLConnection 을 활용한 crawling 에서의 다양한 유틸성 기능
- proxy node client

# mode
crawling.server.mode 설정 값으로 아래 동작 옵션을 설정할 수 있습니다 default: engine
standalone : 단독 서비스로 띄울때 활용
engine :  seomse-system 의 engine 에서 동작


# gradle
implementation 'com.seomse.commons:crawling:0.8.8'
- etc
    - https://mvnrepository.com/artifact/com.seomse.commons/crawling/0.8.8


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
