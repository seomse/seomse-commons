# seomse-cypto

# 개발환경
- open jdk 1.8
- 더이상 사용하지 않고 https://github.com/seomse/seomse-commons 에서 같이 관리됩니다.

# 구성
### 로그인정보 암호화
 - 로그인 정보를 이용하여 키를 생성하고 암복호화 하기 떄문에 방법을 모르면 괜찮아도
   방법을 알면 알고리즘으로 복호화가 가능해서 위험할 수 있음
 - 혼선을 주는 방식이 코드에 있기때문에 코드를 해석 하면 어떻게 원리를 파악 가능  
 - 보안이 심하지 않은 환경에서만 사용 권장
 - 금융권 같이 보안이 심한 방식에서는 사용 X
 
### 문자열 암호화 
 - 키 배열 정보를 활용하여 생성할때마다 같은 문자열이라도 다른값으로 암호화됨
 - 같은 키 배열을 사용하면 복호화 가능함
 - 키 배열은  'x','y','2','Z','a','C','D','I','J','K','L','g','h','i','j','k','l','m','n','o'
 - 위와 같은 형태로  StringCrypto 클래스를 생성하여 사용
 - 위 방식도 보안이 심한곳에서는 사용을 권장하지 않음 
 - 메시지 암호화만 필요한 간단한 환경에서만 사용 권장
 
# gradle
implementation 'com.seomse.commons:crypto:0.1.5'
- etc
  - https://mvnrepository.com/artifact/com.seomse.commons/crypto/0.1.5

# communication
### blog, homepage
- [www.seomse.com](https://www.seomse.com/)
- [runon.io](https://runon.io)
- [github.com/seomse](https://github.com/seomse)
- [github.com/runonio](https://github.com/runonio)

### 카카오톡 오픈톡
 - https://open.kakao.com/o/g6vzOKqb

### 슬랙 slack
- https://seomse.slack.com/
  - 참여코드: runon

### email
 - comseomse@gmail.com
 
 
# main developer
 - macle
    -  [github.com/macle86](https://github.com/macle86)
