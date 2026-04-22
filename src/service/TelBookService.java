package service;

import dto.TelDto;

import java.util.List;

public interface TelBookService {
    public void insert(String name, int age, String address, String phone);

    List<TelDto> getListAll();

    List<TelDto> getListOne(Long id);
}