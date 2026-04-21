package view;

import exception.InputValidation;
import exception.MyException;

import java.util.Scanner;

public class UserView {
    private final Scanner sc;

    public UserView(Scanner scanner) {
        this.sc = scanner;
    }

    public void insert() throws MyException{
        // 검증 인스턴스 생성
        InputValidation validation = new InputValidation();

        System.out.println("== 전화번호 등록 ==");

        // 이름 올바른 값이 들어올 때까지 반복
        boolean check = false;
        do {
            try {
                System.out.print("이름 : "); // 이름은 한글만, 중간 공백 없이
                String name = sc.nextLine();
                validation.nameCheck(name); // 이름 검증
                check = true;
            }catch (MyException e){
                System.out.println(e.getMessage());
            }
        }while (!check);

        System.out.print("나이 : "); // 나이: 0~120세 사이값
        int age = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기
        System.out.print("주소 : ");
        String address = sc.nextLine();
        System.out.print("전화번호 : "); // 전화번호: 010-####-#### 형식으로
        String phone = sc.nextLine();


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