package com.example.demo.controller;

import com.example.demo.model.Animal;
import com.example.demo.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService service;

    @Autowired
    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@Valid @RequestBody Animal animal) {
        Animal savedAnimal = service.saveAnimal(animal);

        return ResponseEntity.ok(savedAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @Valid @RequestBody Animal animal) {
        animal.setId(id);
        Animal updatedAnimal = service.updateAnimal(animal);
        return ResponseEntity.ok(updatedAnimal);
    }

    @PutMapping("/updatePicture/{id}")
    public ResponseEntity<Animal> updatePicture(@PathVariable Long id, @RequestBody String pictureUrl) {
        Animal updatedAnimalPicture = service.updateAnimalPicture(id, pictureUrl);
        return ResponseEntity.ok(updatedAnimalPicture);
    }

    @PutMapping("/updateSound/{id}")
    public ResponseEntity<Animal> updateSound(@PathVariable Long id, @RequestBody String soundUrl) {
        Animal updatedAnimalSound = service.updateAnimalSound(id, soundUrl);

        return ResponseEntity.ok(updatedAnimalSound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable Long id) {
        service.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }


}
