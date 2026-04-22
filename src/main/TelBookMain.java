package main;

import db.DBConn;
import exception.MyException;
import repository.TelBookRepository;
import repository.TelBookRepositoryImpl;
import service.TelBookService;
import service.TelBookServiceImpl;
import view.UserView;

import java.sql.Connection;
import java.util.Scanner;

public class TelBookMain {
    static void main() throws MyException {
        Connection connection = DBConn.getConnection(); // DB 연결
        Scanner sc = new Scanner(System.in);
        TelBookRepository telBookRepository = new TelBookRepositoryImpl(connection);
        TelBookService telBookService = new TelBookServiceImpl(telBookRepository);

        UserView userView = new UserView(sc, telBookService);

        while (true) {
            int input;
            do {
                System.out.println("1. 입력  2. 수정  3. 삭제  4. 전체출력  5. ID검색  6. 종료");
                System.out.print("▶ 메뉴 입력 : ");
                input = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기
            } while (input < 1 || input > 6);

            switch (input) {
                case 1:
                    userView.insert();
                    break;
                case 2:
                    userView.update();
                    break;
                case 3:
                    userView.delete();
                    break;
                case 4:
                    userView.searchAll();
                    break;
                case 5:
                    userView.searchOne();
                    break;
                case 6:
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("1 ~ 6 중 하나를 입력해주세요");
            }
        }
    }
}