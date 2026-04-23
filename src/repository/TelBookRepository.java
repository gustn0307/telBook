package repository;

import dto.TelDto;

import java.util.List;

public interface TelBookRepository {
    int insertData(TelDto dto);

    List<TelDto> findAll();

    List<TelDto> findById(Long id);

    int deleteById(Long id);

    void update(TelDto oldData);

    List<TelDto> findByKeyword(int choice, String keyword);
}
