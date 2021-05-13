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

👉 [시스템 설계](#pill-시스템-설계)

👉 [제공 기능](#pill-제공-기능)

👉 [구성요소별 구현 기능](#pill-구성요소별-구현-기능)

👉 [구현 결과](#pill-구현-결과)

👉 [결론 및 기대효과](#pill-결론-및-기대효과)

👉 [애플리케이션 사용법](#pill-애플리케이션-사용법)

👉 [사용한 공공데이터](#pill-사용한-공공데이터)


---


## :pill: 서비스 개요

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/logo_up.png" width="130%" height="130%"></p>

- 공공데이터를 활용한 의약품 기본 정보, DUR 정보 제공

- 복약 기록, 복약 알림 서비스를 비롯한 복약 관리 기능 제공

- 사용자 맞춤형 DUR 정보 제공



####  DUR이란?

> DUR(Drug Utilization Review, 의약품 적정사용)은 병용 시 또는 어린이, 노인, 임부에게 투여 시 주의해야하는 의약품에 대한 정보



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

## :pill: 시스템 설계

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/시스템설계.png" width="70%" height="70%"></p>

- Android Studio, SQLite, Firebase를 연결하여 데이터를 받아온다.


- 공공데이터포털에서 제공하는 Open API를 사용한다.


- SQLite 데이터베이스에는 DUR 데이터, 사용자 정보를 저장한다.


- Firebase의 Realtime Database에 약 모양 검색을 위한 데이터와 일반의약품의 성분과 관련된 데이터가 저장되어 있으며, Cloud Storage에는 일반의약품의 이미지 파일이 저장되어 있다.

---

## :pill: 제공 기능

- **의약품 상세 정보 검색**
  - 의약품의 제품명 및 성분명, 약모양으로 의약품 정보를 검색할 수 있다. 제공하는 상세 정보로는 제품명, 성분정보, 효능효과, 용법용량, 사용상 주의사항, 식별정보, DUR정보 등이 있다.

- **알약 이미지 검색**
  - 의약품의 모양에 대해 검색을 할 수 있다. 제형, 모양, 색상, 분할선의 버튼 선택으로 조건을 걸어주면 조건에 맞는 의약품 리스트의 결과가 나오게 된다.

- **복약 기록**
  - 달력을 활용하여 복약 기록을 할 수 있는 기능이다. 제품명, 복약 시작 날짜, 종료 날짜, 복용 시각을 설정하면 복약 기록 리스트에 추가되며, 달력에서 날짜 선택을 하게 되면 해당 날짜에 복용해야 하는 의약품 항목들을 볼 수 있다.

- **복용 의약품에 따른 맞춤형 DUR 정보 제공**
  - 복용하는 의약품을 복약 기록 기능에 추가해 놓으면 해당 의약품에 대한 병용 금기, 부작용 등에 대한 주의를 주고, 사전에 설정한 특이사항과 관련이 있는 위험 의약품에 대해 주의를 주는 기능이다.

- **복약 시각 알림**
  - 복약 기록 리스트에 저장된 항목들의 복약 시각에 대한 알림 기능이다. 복약 기록 리스트에 추가하면 그 시각에 복약 시각임을 알리는 알림이 울리게 된다.

- **의약품 즐겨찾기**
  - 자주 찾는 의약품 정보에 대해 즐겨찾기 추가할 수 있는 기능이다. 즐겨찾기를 설정해 놓으면 즐겨찾기 메뉴에서 즐겨찾기로 추가한 의약품의 상세 정보를 볼 수 있다.

---

## :pill: 구성요소별 구현 기능

#### 애플리케이션 구성도 
<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/App Diagram.PNG" width="70%" height="70%"></p>

> 애플리케이션의 기능은 크게 복약 관리 기능, 검색 기능, 사용자 정보 관리 기능으로 나뉜다. MainActivity에 Management, Medicine Search, Mypage가 Fragment로 들어있으며, 각각이 세부적인 기능이 추가되어있다. Management에는 달력을 활용한 복약 기록, 맞춤형 DUR 정보 제공 기능이 있다. Medicine Search에는 제품명 검색(Product Name Search), 성분명 검색(Component Name Search), 약 모양 검색(Shape Search)이 있으며, 검색을 통한 의약품 상세정보 조회가 가능하다. Mypage에는 사용자 정보 조회 및 수정, 즐겨찾기 기능이 있다. 

#### 데이터베이스(SQLite)생성 및 연결하기

Android Studio와 SQLite를 연결하여, 사용자의 정보를 저장하는 데이터베이스와 DUR 데이터를 저장하는 데이터베이스를 만들었다. 
사용자의 정보를 저장하는 데이터베이스(note.db)는 복약 관리에서 사용자가 추가한 항목을 저장하는 테이블(TABLE_NOTE), 사용자가 즐겨찾기에 추가한 항목을 저장하는 테이블(TABLE_BOOKMARK), 사용자의 특이사항을 저장하는 테이블(TABLE_USER)로 구성되어 있다. note.db에 테이블을 만들 때는 SQL문을 사용하여 생성하였다.
~~~javascript
String CREATE_SQL_NOTE = "create table " + TABLE_NOTE + "("
         + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
         + "  CODE TEXT DEFAULT '', "
         + "  NAME TEXT DEFAULT '', "
         + "  CORP TEXT DEFAULT '', "
         + "  CLOCK TEXT DEFAULT '', "
         + "  DATE INTEGER , "
         + "  ALARM INTEGER , "
         + "  DATE2 INTEGER);";
String CREATE_SQL_BOOKMARK = "create table " + TABLE_BOOKMARK + "("
         + " _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
         + "  CODE TEXT DEFAULT '',"
         + "  NAME TEXT DEFAULT '',"
         + "  CORP TEXT DEFAULT '');";

String CREATE_SQL_USER = "create table " + TABLE_USER + "("
         + " _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
         + "  FEATURE TEXT DEFAULT '');";
~~~

DUR 데이터를 저장하는 데이터베이스(dur.db)는 데이터의 형식이 CSV이므로 ‘DB Browser(SQLite)’ 프로그램을 이용하여 SQLite 데이터베이스 파일을 생성하였다. DUR 데이터는 DUR의 종류에 따라 테이블이 분리된 형식으로, 특정연령금기(TABLE_AGE), 용량주의(TABLE_AMOUNT), 노인주의(TABLE_OLD), 투여기간주의(TABLE_PERIOD), 임부금기(TABLE_PREG), 병용금기(TABLE_WITH) 데이터를 각각 저장하는 6개의 테이블로 구성되어 있다.

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/dbbrowser.JPG" width="100%" height="100%"></p>
애플리케이션에서는 이렇게 내부에서 만든 note.db와 외부에서 만든 dur.db, 총 2개의 데이터베이스를 사용한다.

#### Open API 데이터 가져오기

일반의약품의 정보를 가져와서 화면에 출력하기 위해 두 개의 Open API를 사용하였다. 두 개의 Open API 모두 공공데이터포털에서 활용신청을 하여 사용한 API이다. 제공되는 API의 데이터 포맷은 JSON과 XML 형식이다. 서비스 유형은 REST(GET)로 제공되고 활용한 요청 메시지와 응답 메시지는 다음과 같다. 

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/api표1.PNG" width="60%" height="60%"></p>
<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/api표2.PNG" width="60%" height="60%"></p>
이 애플리케이션에서는 API를 XML 문서 형식으로 가져와 사용하는 방식을 사용하였다. 사용한 요청 URL의 형식은 다음과 같다. 

~~~javascript
String address = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"
        + "?serviceKey=" + apiKey
        + "&itemSeq=" + sampleCode;

String address2 = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList"
        + "?ServiceKey=" + apiKey
        + "&item_seq=" + sampleCode;
~~~

Open API의 요청 URL을 API마다 각각 설정하여 저장한다. 위 코드의 요청 URL은 공공데이터포털에서 데이터 활용 신청을 하여 발급받은 서비스 키와 의약품의 품목 기준코드를 결합한 것으로, 서비스 키와 품목 기준코드에 모두 일치하는 데이터만 결과로 나타나게 된다. [그림 5.7]에서 API 2는 '식품의약품안전처_의약품개요정보(e약은요)' API이며, API 3은 '의약품 낱알식별정보(DB) 서비스' API를 말한다. 

요청 URL을 사용하여 데이터를 요청하게 되면 API에서 응답 메시지를 반환하게 된다. 이를 사용하기 위해 두 API의 응답 메시지에서 필요한 정보에 맞는 태그의 내용만 저장하여 사용하였다. (첨부 소스 코드3, 4 참고)

#### Cloud Storage for Firebase에서 이미지 가져오기
의약품의 이미지를 사용하기 위해 Firebase에서 제공하는 Cloud Storage를 사용하였다. 의약품의품목 기준코드를 파일 이름으로 하여 Cloud Storage의 ‘medisine’ 디렉토리에 저장되어 있으며, 이미지의 형식은 png 형식이다. 
의약품의 품목 기준코드에 맞는 이미지를 가져오기 위해 소스 코드에서 변수 ‘image_name’에 이미지 파일의 이름을 포함한 이미지의 경로를 저장하였고, 품목 기준코드와 일치하는 이미지가 존재하면 해당 이미지를 불러온다. (첨부 소스 코드5 참고)
의약품 이미지는 [그림 5.8]과 같이 저장되어 있다.
<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/Cloud Storage.PNG" width="80%" height="80%"></p>

#### Firebase Realtime Database에서 데이터 가져오기
<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/realtimeDBshape.JPG" width="80%" height="80%"></p>
<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/realtimeDBingr.JPG" width="80%" height="80%"></p>

Firebase Realtime Database에 약 모양에 대한 데이터가 [그림 5.9]와 같이 ‘discrimination’ 디렉토리에 저장되어 있으며, 의약품 성분에 대한 데이터가 [그림 5.10]과 같이 ‘ingredient’ 디렉토리에 저장되어 있다. 
약 모양에 대한 데이터는 의약품의 품목 기준코드(code), 제품명(name), 제조 회사명(corp), 색상(color), 제형(form), 분할선(line), 모양(shape)의 정보가 저장되어 있다. DataSnapshot 객체를 통해 데이터베이스로부터 데이터를 가져와 약 모양 검색 기능에서 사용자가 선택한 색상, 종류, 분할선, 모양에 대한 조건에 모두 부합하는 의약품을 찾고, ArrayList에 품목 기준코드, 제품명, 제조 회사명을 저장한다. ArrayList에 저장된 정보를 List 형태로 띄워준다. (첨부 소스 코드6 참고)
의약품 성분 데이터는 품목 기준코드(code), 제품명(name), 제조 회사명(corp), 성분(ingr), 첨가제(add), 첨가제 주의 관련 성분(add_warn)이 저장되어 있다. 이 또한 DataSnapshot 객체를 통해 데이터를 가져오고, 성분 검색 기능과 성분 상세 정보에서 사용된다. 성분 검색 기능에서는 사용자가 검색한 단어가 성분 데이터에 포함되어 있으면 해당 의약품의 품목 기준 코드, 제품명, 회사명을 가져오는 방식으로 설계되어 있으며, 성분 상세정보를 띄워줄 때는 해당 의약품의 품목 기준코드와 데이터베이스의 code가 일치하는 데이터를 가져온다.


---

## :pill: 구현 결과

#### 로딩화면

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/0_intro-crop.PNG" width="20%" height="20%"></p>

#### 초기 설정 화면

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/1_나이 입력-crop.PNG" width="20%" height="20%">          <img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/1_특이사항 선택-crop.PNG" width="20%" height="20%"></p>

#### 복약 기록 화면

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/2_복약관리-crop.PNG" width="20%" height="20%">          <img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/2_복약추가-crop.PNG" width="20%" height="20%">          <img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/2_복약관리2-crop.PNG" width="20%" height="20%"></p>

#### 복약 기록 목록에 따른 맞춤형 DUR 정보 제공 관련 화면

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/2_복약관리3-crop.png" width="20%" height="20%">          <img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/2_주의사항화면-crop.PNG" width="20%" height="20%"></p>

#### 회원 정보 화면

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/5_회원정보-crop.PNG" width="20%" height="20%">          <img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/5_회원정보수정-crop.PNG" width="20%" height="20%"></p>

#### 알림창

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/2_복약알림-crop.PNG" width="20%" height="20%"></p>

#### 일반의약품 검색 화면

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/3_제품명검색-crop.PNG" width="20%" height="20%">          <img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/3_성분명 검색-crop.PNG" width="20%" height="20%">          <img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/3_약모양검색-crop.PNG" width="20%" height="20%"></p>

#### 검색 결과 리스트 화면

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/3_검색 리스트-crop.PNG" width="20%" height="20%"></p>

#### 일반의약품 상세 정보 제공 화면

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/4_상세정보1-crop.PNG" width="20%" height="20%">          <img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/4_상세정보2-crop.PNG" width="20%" height="20%">          <img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/4_상세정보_3-crop.PNG" width="20%" height="20%"></p>

#### 즐겨찾기 화면

<p align="center"><img src="https://github.com/juyeoon/Aigoyak_project/blob/main/image/capture/3_즐겨찾기-crop.PNG" width="20%" height="20%"></p>

---

## :pill: 결론 및 기대효과

---
## :pill: 애플리케이션 사용법

- **안드로이드 스튜디오에서의 사용법**

 1. Aigoyak_project의 소스코드를 내려받는다.
~~~
git clone https://github.com/juyeoon/Aigoyak_project
~~~
 2. 안드로이드 스튜디오를 실행한다.
 3. MainActivity.java 파일의 14행 String KEY 값에 발급받은 API KEY를 작성한다.
~~~javascript
public static String KEY = "발급받은 키";
~~~
 4. 상단탭에서 디바이스 아이콘을 클릭하여 AVD생성 후  실행한다.  <img src="image/avd 사진.PNG"> 

- **API Key 발급**
  - 공공데이터 API Key 받는 방법: [URL](https://jeong-pro.tistory.com/143)
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
