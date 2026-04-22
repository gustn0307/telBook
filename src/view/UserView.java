package view;

import dto.TelDto;
import exception.InputValidation;
import exception.MyException;
import service.TelBookService;

import java.util.ArrayList;
import java.util.List;
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
        searchAll();
        System.out.print("삭제할 ID: ");
        Long id = sc.nextLong();

        int result = service.delete(id);
        System.out.println(result == 1 ? "삭제 완료" : "삭제 실패");
    }

    public void searchAll() {
        List<TelDto> telDtos = new ArrayList<>();
        telDtos = service.getListAll();

        if (!telDtos.isEmpty()) { // 리스트가 비어있는지 확인
            for (TelDto dto : telDtos) { // 전화번호부를 출력
                System.out.println(dto);
            }
//            telDtos.forEach(x -> System.out.println(x)); // stream을 이용한 전화번호 리스트 출력
            return;
        }
        System.out.println("등록된 전화번호가 없습니다.");
    }

    public void searchOne() {

        System.out.print("검색할 ID: ");
        Long id = sc.nextLong();
        sc.nextLine(); // 버퍼 비우기
        List<TelDto> list = service.getListOne(id);

        if (!list.isEmpty()) {
            list.forEach(x -> System.out.println(x));
            return;
        }
        System.out.println("해당 ID가 없습니다.");
    }
}