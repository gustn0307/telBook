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
            psmt.close(); // 사용 후 닫아주기
            rs.close(); // 사용 후 닫아주기
        } catch (Exception e) {
            System.out.println("SEARCH ALL 오류: " + e.getMessage());
        }

        return dtoList;
    }

    @Override
    public List<TelDto> findById(Long id) {
        List<TelDto> dtoList = new ArrayList<>();
        PreparedStatement psmt = null;
        ResultSet rs = null; // 검색 결과 레코드셋을 담을 도구

        try {
            // select * from telbook으로 자바에서 id 일치하는지 찾으면 느리고
            // DB에 바로 쿼리로 날리는게 빠르니까 아래와 같이 구현
            String sql = "SELECT * FROM telbook WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, id);

            rs = psmt.executeQuery();  // SQL 쿼리 실행 결과를 rs에 받는다

            // SQL문이 select문이면 반환한 레코드를 저장할 객체가 필요한데,
            // 이때 레코드를 담는 그릇이 ResultSet이다.
            // ResultSet 객체는 커서를 가지는데, 반환된 직후 ResultSet 객체의 커서는 첫 번째 레코드 이전을 가리킨다.
            // 따라서 첫 번째 레코드를 가리키려면 next() 메서드를 실행해야 한다.
            while (rs.next()) { // 다음 레코드가 있으면 반복문 수행
                TelDto dto = new TelDto(); // 읽어온 레코드를 담을 빈 DTO를 생성
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNumber(rs.getString("phone"));
                dtoList.add(dto); // 전화번호부에 DB에서 읽어온 정보 추가
            }
            psmt.close(); // 사용 후 닫아주기
            rs.close(); // 사용 후 닫아주기
        } catch (Exception e) {
            System.out.println("findById 오류: " + e.getMessage());
        }

        return dtoList;
    }

    @Override
    public int deleteById(Long id) {
        int result = 0; // 실행 결과를 담을 변수
        PreparedStatement psmt = null;

        try {
            String sql = "DELETE FROM telbook WHERE id = ?"; // 쿼리 생성
            psmt = conn.prepareStatement(sql);
            psmt.setLong(1, id);

            result = psmt.executeUpdate(); // 실행 결과 반환(성공하면 숫자 반환함)
            psmt.close(); // 사용 후 닫아주기
        } catch (Exception e) {
            System.out.println("DELETE 오류: " + e.getMessage());
        }

        return result;
    }

    @Override
    public void update(TelDto oldData) {
        PreparedStatement psmt = null;

        try {
            // 쿼리가 헷갈리면 DB에서 쿼리로 실행해서 테스트해보고 그 쿼리를 바탕으로 아래 문장을 만든다.
            // 쿼리가 길어져 중간에 엔터를 치고 '+'로 이어져도 DB에 전달될 때 오류가 생길 수 있어 길어져도 한 줄로 써야 함.
            // 또는 StringBuilder로 SQL 쿼리를 작성해도 된다.
            String sql = "UPDATE telbook SET name = ?, age = ?, address = ?, phone = ? WHERE id = ?"; // 쿼리 생성
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, oldData.getName());
            psmt.setInt(2, oldData.getAge());
            psmt.setString(3, oldData.getAddress());
            psmt.setString(4, oldData.getTelNumber());
            psmt.setLong(5, oldData.getId());

            psmt.executeUpdate(); // 쿼리 실행
            psmt.close(); // 사용 후 닫아주기
        } catch (Exception e) {
            System.out.println("UPDATE 오류: " + e.getMessage());
        }
    }
}
