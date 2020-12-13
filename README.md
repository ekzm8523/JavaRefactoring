# JavaRefactoring
기존에 있던 자바 게임 프로그램을 State패턴에 맞추어 리팩토링하는 레파


# 상태도
<img src="https://user-images.githubusercontent.com/67869514/102001087-509b9f00-3d31-11eb-8b4e-d62364642298.jpg" width="70%" height="70%"></img>


## 프레임을 가진 게임 객체에 대한 상태를 정의 해 그 상태에 맞는 패널을 게임 프레임에 추가 해 주는 방식으로 구조화하였다.




# 전체 시스템 구조도
## 기존의 코드에서 Frame, Panel 이름에 맞지 않게 코드가 몰려 있다.

![그림1](https://user-images.githubusercontent.com/67869514/102001175-3910e600-3d32-11eb-84ae-f344a911cd96.png)

# 개선된 UML
##  MVC패턴 적용


## View
<img width="519" alt="스크린샷 2020-12-13 11 19 36" src="https://user-images.githubusercontent.com/67869514/102001380-2946d100-3d35-11eb-9ad1-045d71724b04.png">

## Model
![그림1](https://user-images.githubusercontent.com/67869514/102001426-80e53c80-3d35-11eb-879c-92ab8b0e1799.png)

## Control
![그림2](https://user-images.githubusercontent.com/67869514/102001429-88a4e100-3d35-11eb-8121-5bdfa6b38d0c.png)
