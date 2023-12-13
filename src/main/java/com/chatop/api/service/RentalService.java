package com.chatop.api.service;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.dto.RentalRequestDTO;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Service
public class RentalService {
    @Value("${server.url}")
    private String serverUrl;
    private final RentalRepository rentalRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public RentalDTO getRental(final Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        return rental.map(r -> {
            RentalDTO rentalDTO = modelMapper.map(r, RentalDTO.class);
            rentalDTO.setOwner_id(r.getOwner().getId());
            rentalDTO.setPicture(r.getPicture());
            rentalDTO.setCreated_at(r.getCreated_at());
            rentalDTO.setUpdated_at(r.getUpdated_at());
            return rentalDTO;
        }).orElse(null);
    }

   public Map<String, List<RentalDTO>> getRentals() {
       List<Rental> rentals = (List<Rental>) rentalRepository.findAll();
       List<RentalDTO> rentalDTOs = rentals.stream()
               .map(rental -> {
                   RentalDTO rentalDTO = modelMapper.map(rental, RentalDTO.class);
                   rentalDTO.setOwner_id(rental.getOwner().getId());
                   rentalDTO.setPicture(rental.getPicture());
                   return rentalDTO;
               })
               .collect(Collectors.toList());
       return Collections.singletonMap("rentals", rentalDTOs);
   }


    public RentalDTO createRental(RentalRequestDTO rentalRequestDTO, Principal principal) {
        try {
            String email = principal.getName();
            User owner = userRepository.findByEmail(email)
                    .orElseThrow(() -> new NoSuchElementException("User not found with email : " + email));
            rentalRequestDTO.setOwner_id(owner.getId());

            // Save the image file
            MultipartFile picture = rentalRequestDTO.getPicture();
            String fileName = StringUtils.cleanPath(picture.getOriginalFilename());
            Path path = Paths.get("src/main/resources/static/images/" + fileName);
            Files.copy(picture.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Generate an absolute URL for the image
            String imageUrl = serverUrl + "/" + fileName;

            // Create a RentalDTO from the RentalRequestDTO
            RentalDTO rentalDTO = new RentalDTO();
            rentalDTO.setId(rentalRequestDTO.getId());
            rentalDTO.setName(rentalRequestDTO.getName());
            rentalDTO.setSurface(rentalRequestDTO.getSurface());
            rentalDTO.setPrice(rentalRequestDTO.getPrice());
            rentalDTO.setPicture(imageUrl); // Set the image URL
            rentalDTO.setDescription(rentalRequestDTO.getDescription());
            rentalDTO.setOwner_id(owner.getId());

            Rental rental = modelMapper.map(rentalDTO, Rental.class);
            rental.setOwner(owner);
            rental.setPicture(rentalDTO.getPicture());
            Rental savedRental = rentalRepository.save(rental);
            RentalDTO savedRentalDTO = modelMapper.map(savedRental, RentalDTO.class);
            savedRentalDTO.setOwner_id(savedRental.getOwner().getId());
            savedRentalDTO.setId(savedRental.getId()); // Set the id in the RentalDTO
            return savedRentalDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error while creating rental: " + e.getMessage());
        }
    }

    public RentalDTO updateRental(Long id, RentalDTO rentalDTO) {
        Optional<Rental> rentalOpt = rentalRepository.findById(id);
        if (rentalOpt.isPresent()) {
            Rental rental = rentalOpt.get();
            LocalDateTime originalCreatedAt = rental.getCreated_at(); // Save the original created_at
            rentalDTO.setOwner_id(rental.getOwner().getId());
            rentalDTO.setPicture(rental.getPicture()); // Preserve the picture path
            Long originalId = rental.getId(); // Save the original id
            modelMapper.map(rentalDTO, rental);
            rental.setId(originalId); // Set the id back to the original id
            rental.setCreated_at(originalCreatedAt); // Set the created_at back to the original value
            rental.setUpdated_at(LocalDateTime.now());
            Rental savedRental = rentalRepository.save(rental);
            return modelMapper.map(savedRental, RentalDTO.class);
        } else {
            return null;
        }
    }
}