package com.chatop.api.controller;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.dto.RentalRequestDTO;
import com.chatop.api.repository.UserRepository;
import com.chatop.api.service.RentalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private UserRepository userRepository;
    private RentalService rentalService;
    private ModelMapper modelMapper;

    public RentalController(UserRepository userRepository, RentalService rentalService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.rentalService = rentalService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createRental(@ModelAttribute RentalRequestDTO rentalRequestDTO, Principal principal) {
        try {
            rentalService.createRental(rentalRequestDTO, principal);
            return new ResponseEntity<>(Collections.singletonMap("message", "Rental created !"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Error while creating rental: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RentalDTO getRental(@PathVariable("id") final Long id) {
        return rentalService.getRental(id);
    }


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String, List<RentalDTO>> getRentals() {
        return rentalService.getRentals();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateRental(@PathVariable("id") final Long id, @ModelAttribute RentalDTO rentalDTO) {
        try {
            RentalDTO updatedRental = rentalService.updateRental(id, rentalDTO);
            if (updatedRental != null) {
                return new ResponseEntity<>(Collections.singletonMap("message", "Rental updated !"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Collections.singletonMap("error", "Rental not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Error while updating rental: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}