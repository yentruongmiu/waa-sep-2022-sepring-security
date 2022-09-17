package edu.miu.productReview.service.impl;

import edu.miu.productReview.domain.Address;
import edu.miu.productReview.dto.AddressDto;
import edu.miu.productReview.repo.AddressRepo;
import edu.miu.productReview.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public AddressDto save(AddressDto address) {
        Address result = addressRepo.save(mapper.map(address, Address.class));
        return mapper.map(result, AddressDto.class);
    }

    @Override
    public List<AddressDto> findAll() {
        List<Address> addresses = new ArrayList<>();
        addressRepo.findAll().forEach(addresses::add);
        return addresses.stream().map(addr -> mapper.map(addr, AddressDto.class)).toList();
    }

    @Override
    public AddressDto findById(int id) {
        return addressRepo.findById(id)
                .map(address -> mapper.map(address, AddressDto.class))
                .orElse(new AddressDto());
    }

    @Override
    public AddressDto update(int id, AddressDto addressDto) {
        Address address = addressRepo.findById(id).get();
        if(address != null) {
            addressDto.setId(address.getId());
            return mapper.map(addressRepo.save(mapper.map(addressDto, Address.class)), AddressDto.class);
        } else throw new IllegalArgumentException();
    }

    @Override
    public void delete(int id) {
        if(addressRepo.existsById(id)) {
            addressRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
