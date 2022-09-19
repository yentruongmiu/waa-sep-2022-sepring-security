package edu.miu.productReview.controller;

import edu.miu.productReview.dto.AddressDto;
import edu.miu.productReview.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<AddressDto> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public AddressDto findById(@PathVariable int id) {
        return addressService.findById(id);
    }

    @PostMapping
    public AddressDto save(@RequestBody AddressDto address) {
        return addressService.save(address);
    }

    @PutMapping("/{id}")
    public AddressDto update(@PathVariable int id, @RequestBody AddressDto address) {
        return addressService.update(id, address);
    }

    @DeleteMapping
    public void delete(@PathVariable int id) {
        addressService.delete(id);
    }
}
