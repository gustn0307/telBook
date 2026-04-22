package repository;

import db.DBConn;
import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelBookRepositoryImpl implements TelBookRepository {
    private final Connection conn; // DB 연결

    public TelBookRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insertData(TelDto dto) {
        int result = 0; // 실행 결과를 담을 변수
        PreparedStatement psmt = null;

        try {
            String sql = "INSERT INTO telbook (name, age, address, phone) VALUES (?, ?, ?, ?)"; // 쿼리 생성
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

    @Override
    public List<TelDto> findAll() {
        List<TelDto> dtoList = new ArrayList<>();
        int result = 0;
        PreparedStatement psmt = null; // 쿼리를 실행할 도구
        ResultSet rs = null; // 검색 결과 레코드셋을 담을 도구

        try {
            String sql = "SELECT * FROM telbook ORDER BY name"; // 쿼리
            psmt = conn.prepareStatement(sql);

            rs = psmt.executeQuery(); // SQL 쿼리 실행 결과를 rs에 받는다

            // rs에 들어간 결과를 telDtos 리스트에 담는다.
            while (rs.next()) { // 다음 레코드가 있으면 반복문 수행
                TelDto dto = new TelDto(); // 읽어온 레코드를 담을 빈 DTO를 생성
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNumber(rs.getString("phone"));
                dtoList.add(dto); // 전화번호부에 DB에서 읽어온 정보 추가
            }
        } catch (Exception e) {
            System.out.println("SEARCH ALL 오류: " + e.getMessage());
        }

        return dtoList;
    }
}
