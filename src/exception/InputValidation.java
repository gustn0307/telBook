package exception;

import java.util.regex.Pattern;

public class InputValidation {
    // 이름 검증: 한글만 허용, 공백 허용 안함
    // 올바른 예 : 강현수
    public void nameCheck(String name) throws MyException {
        // 정규표현식
        boolean check = Pattern.matches("^[ㄱ-ㅎ가-힣]*$", name); // 한글만 허용

        if (!check) {
            throw new MyException("※ 이름은 공백없이 한글로 입력해 주세요.");
        }
    }

    // 나이 검증: 0 ~ 120 사이 값
    public void ageCheck(int age) throws MyException {
        if (age < 0 || age > 120) {
            throw new MyException("※ 나이는 0세부터 120세까지 입니다.");
        }
    }

    // 전화번호 검증: 010-####-#### 형식
    public void telNumCheck(String telNum) throws MyException {
        boolean check = Pattern.matches("(010)-(\\d{4})-(\\d{4})", telNum);

        if (!check) {
            throw new MyException("※ 전화번호는 010-XXXX-XXXX 형식으로 입력해주세요.");
        }
    }
}