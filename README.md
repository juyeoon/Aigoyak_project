# 아이고약!(Aigoyak!)

**공공데이터를 활용한 의약품 정보 기반 복약 관리 서비스**

Medication Managing System Based on Open Data

</br>

창원대학교 컴퓨터공학과 2021 졸업 작품

</br>

🍀 **개발자**

- 팀명: BCD

- 팀원

  - 박수아 ([github](https://github.com/SuAAng))
  - 박주연 ([github](https://github.com/juyeoon))

</br>

🍀 **개발 환경**

- OS: Microsoft Windows 10
- Tools: Android Studio, MySQL DB, SQLite
- Language: Java, XML, SQL, PHP
- Test Device: Android 11.0 / G011A (Google Pixel 2), Android 11.0 / SM-G973N (Galaxy S10)


---

## :pill: 목차

👉 [서비스 개요](#pill-서비스-개요)

👉 [개발 동기](#pill-개발-동기)

👉 [개발 목표](#pill-개발-목표)

👉 [제공 기능](#pill-제공-기능)

👉 [애플리케이션 사용법](#pill-애플리케이션-사용법)

👉 [사용한 공공데이터](#pill-사용한-공공데이터)


---


## :pill: 서비스 개요

<img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/logo_up.png" width="130%" height="130%">

- 공공데이터를 활용한 의약품 기본 정보, DUR 정보 제공

- 복약 기록, 복약 알림 서비스를 비롯한 복약 관리 기능 제공

- 사용자 맞춤형 DUR 정보 제공

</br>

- DUR이란?

  - DUR(Drug Utilization Review, 의약품 적정사용)은 병용 시 또는 어린이, 노인, 임부에게 투여 시 주의해야하는 의약품에 대한 정보



---

## :pill: 개발 동기

- 상비용으로 일반의약품을 구매했을 때, 복용하고 남은 약은 보관하게 된다. 

- 보관 시 포장지는 버리고 알약만 보관하거나, 용도 및 복용 시 주의사항에 맞게 구분하여 보관하지 않는 경우가 많다.

- 주기적으로 복용하여야 하는 약물의 복용 여부와 종류를 잊어버리기 쉽다. 

- 제조약은 병용금기 및 연령대별 주의 약물을 사전에 배제하여 처방하므로 주의를 기울이지 않아도 되지만, 일반의약품은 구매자 스스로가 약물의 성분을 파악하여 적절히 복용하여야 하므로 관련 정보의 접근성을 높일 필요가 있다. 

---

## :pill: 개발 목표

- 공공데이터포털에서 제공되는 의약품 정보와 DUR 정보를 가공하여 종합적인 의약품 정보 제공

- 사용자 맞춤형 복약 관리 서비스 제공

  - 복용 중인 의약품의 복용량 및 복용 시각 알림
  
  - 복용 중인 의약품에 대한 DUR 정보 제공

---

## :pill: 제공 기능

- **의약품 상세 정보 검색**
  - 의약품의 제품명 및 성분명, 약모양으로 의약품 정보를 검색할 수 있다. 제공하는 상세 정보로는 제품명, 성분정보, 효능효과, 용법용량, 사용상 주의사항, 식별정보, DUR정보 등이 있다.

- **알약 이미지 검색**

- **복약 기록**
  - 달력을 활용하여 복약 기록을 할 수 있는 기능이다. 제품명, 복약 시작 날짜, 종료 날짜, 복용 시각을 설정하면 복약 기록 리스트에 추가되며, 달력에서 날짜 선택을 하게 되면 해당 날짜에 복용해야 하는 의약품 항목들을 볼 수 있다.

- **복용 의약품에 따른 맞춤형 DUR 정보 제공**
  - 복용하는 의약품을 복약 기록 기능에 추가해 놓으면 해당 의약품에 대한 병용 금기, 부작용 등에 대한 주의를 주고, 사전에 설정한 특이사항과 관련이 있는 위험 의약품에 대해 주의를 주는 기능이다.

- **복약 시각 알림**
  - 복약 기록 리스트에 저장된 항목들의 복약 시각에 대한 알림 기능이다. 복약 기록 리스트에 추가하면 그 시각에 복약 시각임을 알리는 알림이 울리게 된다.

- **의약품 즐겨찾기**
  - 자주 찾는 의약품 정보에 대해 즐겨찾기 추가할 수 있는 기능이다. 즐겨찾기를 설정해 놓으면 즐겨찾기 메뉴에서 즐겨찾기로 추가한 의약품의 상세 정보를 볼 수 있다.
---

## :pill: 애플리케이션 사용법

- **안드로이드 스튜디오에서의 사용법**

 1. Aigoyak_project의 소스코드를 내려받는다.
~~~
git clone https://github.com/juyeoon/Aigoyak_project
~~~
 2. 안드로이드 스튜디오를 실행한다.
 3. MainActivity.java 파일의 14열 String KEY 값에 발급받은 API KEY를 작성한다.
~~~javascript
public static String KEY = "발급받은 키";
~~~
 4. 상단탭에서 디바이스 아이콘을 클릭하여 AVD생성 후  실행한다.  <img src="image/avd 사진.PNG"> 

- **API Key 발급**

  - API Key 발급 목록
    - [식품의약품안전처_의약품개요정보(e약은요)](https://www.data.go.kr/data/15075057/openapi.do)
    - [의약품 낱알식별정보(DB) 서비스](https://www.data.go.kr/data/15057639/openapi.do)

  - 두 개의 API Key가 동일할 것이다.
---

## :pill: 사용한 공공데이터

- **일반의약품 데이터**

  - [식품의약품안전처_의약품개요정보(e약은요)](https://www.data.go.kr/data/15075057/openapi.do)
  - [의약품 낱알식별정보(DB) 서비스](https://www.data.go.kr/data/15057639/openapi.do)

- **DUR 데이터**

  - [한국의약품안전관리원_DUR정보_의약품금기정보](https://www.data.go.kr/data/15039484/fileData.do)

  - [한국의약품안전관리원_DUR정보_약물금기정보](https://www.data.go.kr/data/15039485/fileData.do)

  - [한국의약품안전관리원_DUR정보_병용주의약물정보](https://www.data.go.kr/data/15039472/fileData.do)

  - [한국의약품안전관리원_DUR정보_병용금기약물정보](https://www.data.go.kr/data/15039486/fileData.do)

  - [한국의약품안전관리원_DUR정보_용량주의](https://www.data.go.kr/data/15039480/fileData.do)

  - [한국의약품안전관리원_DUR정보_임부주의약물](https://www.data.go.kr/data/15039479/fileData.do)

  - [한국의약품안전관리원_DUR정보_임부연령금기정보](https://www.data.go.kr/data/15039483/fileData.do)

  - [한국의약품안전관리원_DUR정보_어린이주의약물](https://www.data.go.kr/data/15039470/fileData.do)

  - [한국의약품안전관리원_DUR정보_노인주의약물](https://www.data.go.kr/data/15039478/fileData.do)

출처: 공공데이터포털(https://www.data.go.kr/)
