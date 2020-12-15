# JavaRefactoring
기존에 있던 자바 게임 프로그램을 State패턴에 맞추어 리팩토링하는 레파


# 상태도
<img src="https://user-images.githubusercontent.com/67869514/102001087-509b9f00-3d31-11eb-8b4e-d62364642298.jpg" width="70%" height="70%"></img>


## 프레임을 가진 게임 객체에 대한 상태를 정의 해 그 상태에 맞는 패널을 게임 프레임에 추가 해 주는 방식으로 구조화하였다.




# 전체 시스템 구조도
## 기존의 코드에서 Frame, Panel 이름에 맞지 않게 코드가 몰려 있다.

![그림1](https://user-images.githubusercontent.com/67869514/102001175-3910e600-3d32-11eb-84ae-f344a911cd96.png)

# 개선된 UML
##  MVC패턴 적용


## View
<img width="519" alt="스크린샷 2020-12-13 11 19 36" src="https://user-images.githubusercontent.com/67869514/102001380-2946d100-3d35-11eb-9ad1-045d71724b04.png">

## Model
![그림1](https://user-images.githubusercontent.com/67869514/102001426-80e53c80-3d35-11eb-879c-92ab8b0e1799.png)

## Control
![그림2](https://user-images.githubusercontent.com/67869514/102001429-88a4e100-3d35-11eb-8121-5bdfa6b38d0c.png)



# 주요 리팩토링 내용

## 자주 사용하는 객체에 대해서 싱글톤 패턴으로 구조화 하였다.
![그림1](https://user-images.githubusercontent.com/67869514/102166707-1288b180-3ed0-11eb-919d-941048ee91a5.png)

기존에 MainPanel에 모여있던 코드를 메서드 추출 및 필드 이동하여 코드를 분리시켰고 메서드를 메서드 객체로 전환하면서 기능을 눈에 보기 쉽게 구현하였다. 또 한 해당하는 클래스의 메서드 내용에 맞게 메소드 명을 수정하였고 지역변수에 다시 대입되는 부분이 가독성을 해치기 때문에 별도의 변수를 설정하여 의미를 부여하였다. 
자주 사용되는 Button ,Label 같은 경우를 하나의 메서드에서 반환하게 수정하였으나 상태 변경 메서드와 값 변환 메서드가 함께 있어서 분리하는 리펙토링 하였다. 또 한 메서드에서 해당 필드에 접근 할 때 직접 접근하는 것이 아닌 읽기/쓰기 메서드를 작성해서 두 메서드를 통해서만 필드에 접근하게 하였다. 

 - State 패턴으로 전환
디자인 패턴이 적용 되어 있지 않았던 기존 프로젝트를 State 패턴으로 바꾸기 위해 기초 틀을 마련. 하나의 프레임을 가지는 Game 객체를 생성 한 뒤 Game 상태에 맞는 패널을 붙여주는 방식으로 리팩토링.
- 알고리즘 전환

isRankIn 메소드가 구조상 이상한 곳에서 동작되고 있어 setNewRank 메소드 안에서 동작하게 수정 함.

- 클래스 추출, 필드이동, 메서드 이동

RankPanel 클래스에 랭킹을 다루는 메소드가 있어 어울리지 않는 것 같아 Rank 클래스를 만들어 랭킹 입력 메소드, 데이터베이스 연결 메소드를 분리해 옮겼다.

Controller / 내부 알고리즘 전체 변환
방향별로 코드가 따로 설계되어있어 코드를 바꾸면 네방향 전부 바꿔야하는 불편함이 있었다. 그래서 코드를 병합하고 방향별로 바뀌게끔 알고리즘을 바꾸었다.

Model / 변수 객체화

일반 Int값으로 선언되어 x,y좌표만 기억하는 코드에서 객체의 부모클래스를 만들고 그 부모를 상속받는 객체들로 변경하여 좀 더 객체지향적이게 바꾸었다

View / 클래스 추출, 필드 이동, 메서드 이동

PlayPanel 클래스에 배경과 객체들을 그려주는 코드가 있는건 아니라고 판단하여 View클래스를 만들어 Map과 이미지 관련 코드를 이동시켰다.. 


## 1. State 패턴 전체적인 틀 구현

이전에는 디자인 패턴이 적용되어 있지 않고 하나의 클래스에 대부분의 기능이 몰려있는 구조였다.

![그림](https://user-images.githubusercontent.com/67869514/102166944-a65a7d80-3ed0-11eb-87cd-76549400a661.png)

이후에는 프레임을 가진 게임 객체에 대한 상태를 정의해 그 상태에 맞는 패널을 게임 프레임에 추가 해 주는 방식으로 구조화하였다.
예를 들어 게임의 상태가 Main 인 경우에는 Main 패널을 프레임에 붙여주고, 랭킹 버튼을 누르면 게임이 Rank 상태로 바뀌어 현재 프레임에 있는 Main 패널을 지운 뒤 Rank 패널을 붙여주었다.
덕분에 기존의 코드에서는 새로운 기능을 추가하기 굉장히 어려웠지만 지금은 새로운 기능을 추가하고 싶은 경우 그 상태와 패널만 추가해주면 가능하게 구조를 만들었다.

<img width="656" alt="스크린샷 2020-12-15 12 26 29" src="https://user-images.githubusercontent.com/67869514/102166982-c4c07900-3ed0-11eb-81b7-8032af947caf.png">

## 2. 플레이 패널을 제외한 모든 패널에 싱글톤 패턴 적용

<img width="646" alt="스크린샷 2020-12-15 12 28 36" src="https://user-images.githubusercontent.com/67869514/102167106-10732280-3ed1-11eb-9034-a59806870284.png">

## 3. 랭킹 관련 리팩토링

이전에는 MainFrame 클래스에 랭킹 관련 메소드가 있어 Rank 클래스와 RankPanel 클래스를 만들어 옮겼다.

![그림1](https://user-images.githubusercontent.com/67869514/102167187-3dbfd080-3ed1-11eb-9dfd-e26a0a189414.png)

이후에는 RankPanel 클래스에는 이전 랭킹을 지우는 메소드, 랭킹을 출력하는 메소드, 랭킹 진입 여부를 출력하는 메소드를 넣었고 Rank 클래스에는 데이터베이스를 연결 해 주는 메소드와 점수를 데이터베이스에 입력하는 메소드를 넣었다.

![그림](https://user-images.githubusercontent.com/67869514/102167209-4fa17380-3ed1-11eb-98ba-8c52f5c586ac.png)
![그림1](https://user-images.githubusercontent.com/67869514/102167700-4369e600-3ed2-11eb-9559-02faf143e077.png)


## 4. View 패키지 속 클래스 변수명, 메소드명 정리

이전에는 변수가 통일되지 않고 식별만 하기 위한 숫자가 무분별하게 사용되었다. 
![그림1](https://user-images.githubusercontent.com/67869514/102167276-7364b980-3ed1-11eb-9f35-b85f95e5aecb.png)
![그림2](https://user-images.githubusercontent.com/67869514/102167279-7495e680-3ed1-11eb-9ca2-e2838f426f0a.png)

이후에는 default 패키지에서 모든 클래스 파일을 만들어 리팩토링을 한 뒤 Model, View, Contoller 패키지로 클래스 파일을 나누었고 View 패키지에 있는 클래스 파일들의 변수 명, 메소드 명 등을 정리하였다.

![그림1](https://user-images.githubusercontent.com/67869514/102167337-8f685b00-3ed1-11eb-8ddd-aabbca0d9ac5.png)
![그림2](https://user-images.githubusercontent.com/67869514/102167339-91321e80-3ed1-11eb-9e03-1df850fe8745.png)

## 5. 추가 리팩토링
•	default 패키지에서 클래스 파일을 나누어 리팩토링 후 Model, View, Controller 패키지로 클래스 파일을 나눈 뒤 생긴 오류를 수정하였다.
•	GameClear, GameOver 패널에 있는 버튼에 대한 생성을 메소드화 했다.
•	isRankIn 메소드가 구조상 이상한 곳에서 동작되고 있어 setNewRank 메소드 안에서 동작하게 수정하였다.

## 6. 모든 임시변수 객체화

•	int player_x, int player_y → Player객체로 변환
•	int BoxX[] ,int BoxY[] → Bone객체로 변환
•	int GoalX[], int GoalY[] → RiceBowl객체로 변환

![그림1](https://user-images.githubusercontent.com/67869514/102167551-fbe35a00-3ed1-11eb-8048-71ade0829257.png)
![그림2](https://user-images.githubusercontent.com/67869514/102167555-fd148700-3ed1-11eb-8c0c-64bd6bbaf277.png)

Player, Grass, Bone, Brick, RiceBowl, MapOutside객체는 모두 GameObject를 부모로 두게 하고 공통적인 정보 좌표값, 라벨을 캡슐화 하였습니다.

<img width="588" alt="스크린샷 2020-12-15 12 36 20" src="https://user-images.githubusercontent.com/67869514/102167639-26351780-3ed2-11eb-9109-949e5e5fc902.png">

 객체화를 하니 PlayPanel안에서 이루어지던 로직들을 분배하는게 한층 더 쉬워졌습니다.
 
 ![그림1](https://user-images.githubusercontent.com/67869514/102167700-4369e600-3ed2-11eb-9559-02faf143e077.png)

PlayPanel안에서 위로 올라가던 move로직을 별도의 컨트롤러 클래스의 메소드로 옮겼습니다. (move메소드 로직이 알고리즘 변환기법으로 인해 바뀌어 선언부만 캡쳐하였습니다.)

![그림1](https://user-images.githubusercontent.com/67869514/102168505-b162dd00-3ed3-11eb-863f-b1b1764c5bbe.png)

GameManager는 가장 상위 레벨의 매니저이며 State를 관리하고 게임 진행상황이 나와있는 BarObject도 관리합니다. SoundManager는 Sound를 관리합니다. Bgm과 Bark사운드는 SoundManager로 이용 필요할때마다 getInstance화 합니다.( 없던 상태를 만든 구조라서 이전 코드가 없습니다. )

<img width="566" alt="스크린샷 2020-12-15 12 48 38" src="https://user-images.githubusercontent.com/67869514/102168604-dd7e5e00-3ed3-11eb-85b7-7225c86fb506.png">

다음으로 가장 문제였던 move 메서드입니다. 이 또한 PlayPanel안에 있고 확장성을 고려하지않아 비슷한듯 하지만 규칙적이지 않은 부분이 많았습니다. 객체화를 하고 게임 프로그램인 만큼 방향성의 약속을 적용하여 12시는 0, 3시는 1, 6시는 2, 9시는 3 으로 통일을 하였습니다. 방향별 케이스마다 같은 코드로 돌아가도록 로직을 변경하고 Test를 한 뒤에 이상이 없는 걸 확인하고 방향별 코드를 통합하였습니다

## 변경 전
![그림1](https://user-images.githubusercontent.com/67869514/102168659-f71fa580-3ed3-11eb-81d0-e7fb1654e5f8.png)

## 변경 후
![그림1](https://user-images.githubusercontent.com/67869514/102168716-1a4a5500-3ed4-11eb-899b-74fcbd956023.png)

Undo의 Case는 약속된게 아닌 위, 아래, 오른쪽 옆 순으로 1,2,3,4 인 Case문을 아까 말했던 GameDirection의 0은 12시 부터 시계방향의 약속을 적용해 각 케이스별 코드를 맞추고 Direction 만 변경해주면 그 Direction별 로직만 실행이 되도록 알고리즘 변경을 하였습니다.

## 변경 전
![그림](https://user-images.githubusercontent.com/67869514/102168802-406ff500-3ed4-11eb-959b-c60c5b0b2377.png)

## 변경 후
![그림2](https://user-images.githubusercontent.com/67869514/102168821-49f95d00-3ed4-11eb-9689-e4f0b124079b.png)

isGameOver 메서드도 마찬가지로 Direction을 도입하여 알고리즘 변경기법을 적용하였습니다.

## 변경 전
![그림1](https://user-images.githubusercontent.com/67869514/102168905-6f866680-3ed4-11eb-9af0-1cec42772dda.png)

## 변경 후
![그림2](https://user-images.githubusercontent.com/67869514/102168909-70b79380-3ed4-11eb-9dac-1a855cc8b3a6.png)

객체를 컨트롤하는 메서드들은 Controller에서 관리해야 한다고 생각하여 GameController라는 클래스를 생성하고 싱글턴 패턴을 적용하였습니다. 그리고 movePlayer(), Undo(), isGameOver(), isGameClear()를 GameController클래스로 메서드 이동 시켰습니다.

map을 그려주는 로직이 PlayPanel의 생성자에 있었는데 GameView클래스를 만들어 그안의 메소드로 객체를 그려주는 DrawObject와 맵을 그려주는 DrawMap메소드를 만들어서 좀 더 객체지향적이게 바꾸었습니다.

## 변경 전
![그림1](https://user-images.githubusercontent.com/67869514/102168987-9349ac80-3ed4-11eb-9b25-3c81f4cdab04.png)

## 변경 후
![그림1](https://user-images.githubusercontent.com/67869514/102169008-a0669b80-3ed4-11eb-8abc-99ffd40c8aae.png)


MainFrame에 있던 키 리스너를 State패턴으로 변경해 전체 시스템구조가 변경되어서 여기저기 옮겨 다녔습니다. 그래서 GameListener클래스를 생성하여 메서드 이동을 하였습니다.

## 변경 전
![그림1](https://user-images.githubusercontent.com/67869514/102169059-bb391000-3ed4-11eb-8969-aa381ce5bbc5.png)

## 변경 후
![그림2](https://user-images.githubusercontent.com/67869514/102169091-ca1fc280-3ed4-11eb-8f67-d56e3fb6e6f8.png)

PlayPanel안의 view메소드를 GameView클래스로 이동해 키입력이 되었을때의 View(player이동) 처리를 GameView.inputKeyValue에서 처리하도록 변경함. ( + direction 38,40으로 되어있던걸 주석없이 볼 수있도록 GameDirection.D_UP 이런식으로 변경)

## 변경 전
![그림1](https://user-images.githubusercontent.com/67869514/102169163-eae81800-3ed4-11eb-8ec3-6cadda5b7fd6.png)

## 변경 후
![그림1](https://user-images.githubusercontent.com/67869514/102169175-f50a1680-3ed4-11eb-8472-fd9f9a6c736b.png)

# 최종적인 PlayPanel 변화

## 변경 전
![그림1](https://user-images.githubusercontent.com/67869514/102169307-40bcc000-3ed5-11eb-85f2-051801838e51.png)

## 변경 후
![그림2](https://user-images.githubusercontent.com/67869514/102169314-431f1a00-3ed5-11eb-8caf-d4490222cdfb.png)

게임의 플레이 로직과 그려주는 코드가 몰려있어 복잡했던 더미코딩에서 조금 더 객체지향적인 코드로 바뀌었다.

# ImageIcon 분리

기존의 코드는 이미지값만 다루고 이미지값만 다루고 나머지는 동일하게 작동하여 중복코드를 줄이기 위해 GameIcon 클래스를 선언하고 라운드가 지나감에 따라 자주 사용하는 Icon같은 경우는 싱글톤 패턴을구현하였다.

![그림1](https://user-images.githubusercontent.com/67869514/102169406-7497e580-3ed5-11eb-80c1-5aae8d823fd9.png)

![그림2](https://user-images.githubusercontent.com/67869514/102169411-7661a900-3ed5-11eb-95cd-e3727b77450e.png)

중복코드를 줄이고 이미지 아이콘마다 별도의 크기를 결정해 주었다.
![그림1](https://user-images.githubusercontent.com/67869514/102169459-9abd8580-3ed5-11eb-8199-66050b8e5887.png)

# 2.	단순 반복 Button 메소드 리팩토링

![그림1](https://user-images.githubusercontent.com/67869514/102169486-aad56500-3ed5-11eb-801c-392f1596af8c.png)

평상시와 마우스가 올라갔을때 이미지가 바뀌기 위해서 생성자에서 이미지 아이콘 2개를 받는다.
또 한 set버튼 안에서 필드값에 접근 할때 직접 가져오는 것이 아닌 get/set을 통해 받아 오기위해
캡슐화 작업을 하였다. 각 버튼마다 리스너의 실행해야할 State메소드가 다르기 때문에 별도로 붙이는 것으로 결정하였다.

![그림1](https://user-images.githubusercontent.com/67869514/102169679-0c95cf00-3ed6-11eb-82fb-b650d28043be.png)

# 3.	단순반복 Label 메소드 리팩토링

![그림1](https://user-images.githubusercontent.com/67869514/102169705-246d5300-3ed6-11eb-88c2-c9cab73f66f2.png)

라벨이름과 같이 이미지 이름을 받고 생성자를 오버로딩하여 라벨 아이콘을 쓰는 아이콘과 쓰지 않는 아이콘, swingConstants를 사용하는 라벨들을 구분하였다. 또 한 기존의 set메소드에서 get메소드 같이 return 하는 부분이 있어 get/set을 나누는 리펙토링을 하였다.

<img width="517" alt="image" src="https://user-images.githubusercontent.com/67869514/102169733-33540580-3ed6-11eb-8ef2-f9ddc0d5c6ab.png">



4. 추가 리팩토링
•	Controller 안에 있는 주석, 변수, 메소드 리팩토링하였다.
•	단순 상수로 정의되어 있던 뼈다귀, 잔디를 GameObject에 들어있는 객체들로 바꾸었다.
•	Undo 객체화 한 것을 controller에서 연결하여 기능 수행하게 하였다.

##	PlayMusic 클래스 분리

<img width="664" alt="스크린샷 2020-12-15 13 07 25" src="https://user-images.githubusercontent.com/67869514/102169875-7ada9180-3ed6-11eb-8381-db8c2610500f.png">

기존 PlayMusic 클래스는 BGM과 Bark객체가 가지는 기능들을 담았다. startMusic()과 moveMusic()을 하나로 합치고, 이와 더불어 stopMusic()과 start()의 기능을 갖는 부모 클래스로 하고, 그 역할에 맞게 이름을 Sound로 바꾸어 주었다.

<img width="649" alt="스크린샷 2020-12-15 13 07 44" src="https://user-images.githubusercontent.com/67869514/102169896-862dbd00-3ed6-11eb-9eec-50e4a83d08fe.png">

또한 기존의 BGM과 Bark 객체를 클래스화하여 BackgroundMusic과 BarkSound의 이름으로 Sound클래스의 상속을 받는 구조로 바꾸었다. 또한 BackgroundMusic의 loopCilp()을 따로 정의했다.
마지막으로 쓸데없는 주석은 삭제해 주었다.

## TimeThread 클래스 리팩토링

<img width="625" alt="스크린샷 2020-12-15 13 08 16" src="https://user-images.githubusercontent.com/67869514/102169929-99408d00-3ed6-11eb-83cf-ade76ed82e90.png">

기존 TimeThread클래스는 TimeThread의 역할과 맞지 않게 다른 Panel의 멤버 변수를 갖고 있었고, 이 변수들을 이용한 메소드가 있었다. 이 변수들과 이 변수들을 이용한 메소드를 제거하고 State 패턴 구조에 맞게 GameOverState, GameClearState에서 이 변수들이 나타날 수 있게 했다.

<img width="649" alt="스크린샷 2020-12-15 13 09 42" src="https://user-images.githubusercontent.com/67869514/102170039-cd1bb280-3ed6-11eb-9d52-1f136daae694.png">

TimeThread에 싱글톤 패턴을 적용하였고, 리팩토링된 아이콘과 폰트 적용 방식에 따라 TimeThread의 아이콘과 폰트도 이 방식에 적용시키고 패널 초기화 부분을 생성자에 포함시켜주었다.
마지막으로 쓸데없는 주석은 삭제해 주었다.

## BarObject로 클래스 추출

<img width="554" alt="스크린샷 2020-12-15 13 10 37" src="https://user-images.githubusercontent.com/67869514/102170091-ede40800-3ed6-11eb-87b1-91a2d21b387f.png">

기존 MainFrame클래스에서 게임 진행 정보인 level, nMove, nScore를 직접 다뤘었는데 이 변수들이 다뤄질 땐 함께 다뤄지므로 한 클래스에서 관리할 수 있게 BarObject클래스로 추출하였다.

<img width="126" alt="image" src="https://user-images.githubusercontent.com/67869514/102170118-fb00f700-3ed6-11eb-9197-f8a5dc158d68.png">

또한 이 변수들을 다른 클래스에서 직접 다루지 못하게 캡슐화 하고 값 수정을 메소드화 시켜주었다.

<img width="590" alt="스크린샷 2020-12-15 13 11 20" src="https://user-images.githubusercontent.com/67869514/102170135-06ecb900-3ed7-11eb-907b-4b94473aec0c.png">

점수계산 부분도 이 변수들을 이용하기 때문에 메소드화 해서 BarObject에서 정의하였다

## Model 패키지 내 클래스들의 변수명, 메소드명 수정

기존 Model 패키지에 속해 있던 클래스들이 한 눈에 봐도 의미를 알 수 없는 여러 변수들과 메소드들의 이름을 무슨 용도로 쓰이는지 파악하기 쉽게 변경해주었다.
