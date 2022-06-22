import java.util.ArrayList;
import java.util.List;

/**
 * [문제 : 각 고객(Client), 메뉴판(Menu), 요리사(Shef), 요리(Dish) 객체를 통해 요리를 주문하는 클래스를 설계 및 구현 하십시오.]
 * # 각 객체별 기능 요구사항
 * - 고객은 요리사에게 요리를 주문 할 수 있습니다.
 *
 * - 고객은 주문 시 메뉴판을 이용하여 주문 해야 합니다.
 *
 * 요리사는 고객에게 전달 받은 메뉴판을 통해 조리를 할 수 있습니다.
 * - 조리 이후 요리사는 조리 된 요리를 고객에게 전달 합니다.
 *
 * # 구현 시 특이사항
 *
 * - 각 클래스간 OOP 지향 및 SOLID 원칙이 반영 될 수 있도록 설계를 진행해주세요. (* 상기 명시 된 4개의 클래스 외 설계를 함에 있어 추가적인 클래스 생성이 가능 합니다.)
 *
 * - main 함수는 (public static void main(String[] args))  Client 객체에 선언 되어 있으며, 주문 시 요리 선택은 Client 객체에서 정의 해주세요.
 *
 * - 메뉴판 내에는 각 요리명, 음식분류, 먹는 방식 등이 명시 되어 있습니다. (ex : 갈비찜/한식/젓가락, 파스타/양식/포크, 마파두부/중식/숟가락)
 *
 * - 주방장은 음식분류에 따라 조리가 가능한 각각의 요리사가 존재 하며, 더 늘어날 수 도 있습니다. (ex : 한식요리사, 양식요리사, 중식요리사)
 *
 * - 고객이 요리를 전달 받고 메시지를 출력 할 때 주문한 음식 이름과 먹는 방법도 함께 출력해주세요
 *
 * * 출력 되는 메시지 (예시)
 *
 * 고객은 갈비찜을 주문하였습니다.
 *     한식 요리사는 갈비찜 주문을 받았습니다.
 *     한식 요리사는 갈비찜을 만들었습니다.
 *     고객은 주문한 갈비찜을 받았으며, 젓가락을 먹었습니다.
 *
 * #산출물
 * 1. 요구사항에 맞게 구현 된 java 파일
 * 2. 클래스 다이어그램(이미지파일)
 */


public class Main {

    public static void main(String[] args) {

        Client client = new Client();
        client.order("갈비찜");
    }

}


class CreateConfig {

    List<MenuItem> items = new ArrayList<>();

    public List<MenuItem> items() {
        items.add(new MenuItem("갈비찜", "한식", "젓가락"));
        items.add(new MenuItem("파스타", "양식", "포크"));
        items.add(new MenuItem("마파두부", "중식", "숟가락"));
        return items;
    }

    public Chef chef(MenuItem menuItem) {
        if (menuItem.getType().equals("한식")) {
            return new KoreanChef();
        } else if (menuItem.getType().equals("양식")) {
            return new WesternChef();
        } else if (menuItem.getType().equals("중식")) {
            return new ChineseChef();
        } else {
            throw new IllegalStateException("해당 요리는 주문 할 수 없습니다.");
        }
    }
}

class Client {

    CreateConfig createConfig = new CreateConfig();
    Menu menu = new Menu(createConfig.items());

    public void order(String menuName) {

        System.out.println("고객은 " + menuName + "을 주문하였습니다.");
        MenuItem menuItem = menu.choose(menuName);

        Chef chef = createConfig.chef(menuItem);
        System.out.println(chef + "는 " + menuItem.getName() + " 주문을 받았습니다.");

        Dish dish = chef.makeDish(menuItem);
        System.out.println(chef + "는 " + dish.getName() + "을 만들었습니다.");

        System.out.println("고객은 주문한 " + menuName + "을 받았으며, " + dish.getHowTo() + "을 사용하였습니다.");

    }
}


class Menu {

    private List<MenuItem> items;

    public Menu(List<MenuItem> items) {
        this.items = items;
    }

    public MenuItem choose(String name) {
        for (MenuItem item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        throw new IllegalStateException("해당주문의 요리는 없습니다.");
    }

}


class MenuItem {

    private String name;
    private String type;
    private String howTo;

    public MenuItem(String name, String type, String howTo) {
        this.name = name;
        this.type = type;
        this.howTo = howTo;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getHowTo() {
        return howTo;
    }

}

class Dish {

    private String name;
    private String type;
    private String howTo;


    public Dish(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.type = menuItem.getType();
        this.howTo = menuItem.getHowTo();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getHowTo() {
        return howTo;
    }
}


interface Chef {
    Dish makeDish(MenuItem menuItem);

}

class KoreanChef implements Chef {
    @Override
    public Dish makeDish(MenuItem menuItem) {
        return new Dish(menuItem);
    }

    @Override
    public String toString() {
        return "한식요리사";
    }
}

class WesternChef implements Chef {
    @Override
    public Dish makeDish(MenuItem menuItem) {
        return new Dish(menuItem);
    }

    @Override
    public String toString() {
        return "양식요리사";
    }
}

class ChineseChef implements Chef {
    @Override
    public Dish makeDish(MenuItem menuItem) {
        return new Dish(menuItem);
    }

    @Override
    public String toString() {
        return "중국요리사";
    }
}