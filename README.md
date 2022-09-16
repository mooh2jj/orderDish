# orderDish
OOP 자바 프로그램 예시

#### 클래스 다이어그램

![image](https://user-images.githubusercontent.com/62453668/175045224-23b4042d-353d-4479-a291-3aa2c2fa1126.png)


#### SOLID 원칙
1. `SRP`: Client에는 order 행위, Menu에는 choose 행위 등 클래스에 맞는 책임을 할당해준다.
2. `OCP`: Chef라는 추상화클래스(인터페이스)를 사용하여 chef를 구현한 클래스가 확장되어도 chef를 사용한 client에게는 변경 닫혀있다.
3. `LSP`: Chef 객체를 생성할 때 chef를 구현한 하위클래스들로 치환해서 생성해도 문제가 없다.
4. `ISP`: Chef 인터페이스에는 최소한 메서드만 있다.
5. `DIP`: 상위객체인 Client가 Chef라는 추상화클래스(인터페이스)에 의존하여 구현클래스인 하위객체들이 Clinet에 의존하여 의존관계를 역전시킬 수 있다.

#### 참고: <객체지향 사실과 오해>


