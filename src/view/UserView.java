package view;

import exception.InputValidation;
import exception.MyException;
import service.TelBookService;

import java.util.Scanner;

public class UserView {
    private final Scanner sc;
    private final TelBookService service;

    public UserView(Scanner scanner, TelBookService service) {
        this.sc = scanner;
        this.service = service;
    }

    public void insert() throws MyException {
        // 검증 인스턴스 생성
        InputValidation validation = new InputValidation();

        String name = "";
        int age = 0;
        String address = "";
        String phone = "";

        System.out.println("== 전화번호 등록 ==");
        // 이름 올바른 값이 들어올 때까지 반복
        boolean check = false;
        do {
            try {
                System.out.print("이름 : "); // 이름은 한글만, 중간 공백 없이
                name = sc.nextLine();
                validation.nameCheck(name); // 이름 검증
                check = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (!check);

        boolean ageOk = false;
        do {
            try {
                System.out.print("나이 : "); // 나이: 0~120세 사이값
                age = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기
                validation.ageCheck(age);
                ageOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (!ageOk);

        System.out.print("주소 : ");
        address = sc.nextLine();

        boolean phoneOk = false;
        do {
            try {
                System.out.print("전화번호 : "); // 전화번호: 010-####-#### 형식으로
                phone = sc.nextLine();
                validation.telNumCheck(phone);
                phoneOk = true;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        } while (!phoneOk);

        service.insert(name, age, address, phone); // 서비스에게 입력받은 값 전달
    }

    public void update() {
    }

    public void delete() {
    }

    public void searchAll() {
    }

    public void searchOne() {
    }
}