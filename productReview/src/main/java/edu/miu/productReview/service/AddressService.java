package edu.miu.productReview.service;

import edu.miu.productReview.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto save(AddressDto address);
    List<AddressDto> findAll();
    AddressDto findById(int id);
    AddressDto update(int id, AddressDto addressDto);
    void delete(int id);
}
