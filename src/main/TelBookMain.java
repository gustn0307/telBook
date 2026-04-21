package main;

import java.util.Scanner;

public class TelBookMain {
    static void main() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int input;
            do {
                System.out.println("1. 입력  2. 수정  3. 삭제  4. 전체출력  5. ID검색  6. 종료");
                System.out.print("▶ 메뉴 입력 : ");
                input = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기
            }while (input < 1 || input > 6);

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
