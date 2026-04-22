package service;

import dto.TelDto;
import repository.TelBookRepository;

import java.util.List;

public class TelBookServiceImpl implements TelBookService {
    private final TelBookRepository repository;

    public TelBookServiceImpl(TelBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insert(String name, int age, String address, String phone) {
        TelDto dto = new TelDto(0L, name, age, address, phone); // 전달받은 인자를 필드로 가지는 TelDto 인스턴스 생성
        int result = repository.insertData(dto); // TelBookRepository에 생성한 인스턴스 전달
        if (result > 0) {
            System.out.println("정상적으로 저장되었습니다.");
        }
    }

    @Override
    public List<TelDto> getListAll() {
        return repository.findAll();
    }

    @Override
    public List<TelDto> getListOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public int delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public void update(TelDto oldData) {
        repository.update(oldData);
    }
}