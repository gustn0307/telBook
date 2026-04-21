package repository;

import db.DBConn;
import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TelBookRepositoryImpl implements TelBookRepository {

    @Override
    public int insertData(TelDto dto) {
        Connection conn = DBConn.getConnection(); // 1. DB 연결

        int result = 0; // 실행 결과를 담을 변수
        PreparedStatement psmt = null;

        try {
            String sql = "INSERT INTO telbook (name, age, address, phone) VALUES (?, ?, ?, ?)"; // 2. 쿼리 생성
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getTelNumber());

            result = psmt.executeUpdate(); // 실행 결과 반환(성공하면 숫자 반환함)
            psmt.close(); // 사용 후 닫아주기
        } catch (Exception e) {
            System.out.println("INSERT 오류: " + e.getMessage());
        }

        return result;
    }
}
