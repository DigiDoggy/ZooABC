package com.example.demo.service;

import com.example.demo.exceptions.AnimalNotFoundException;
import com.example.demo.model.Animal;
import com.example.demo.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Animal animal) {
        Animal existingAnimal = animalRepository.findById(animal.getId())
                .orElseThrow(() -> new AnimalNotFoundException("Animal not found with id: " + animal.getId()));

        System.out.println("ExistingAnimal: " + existingAnimal.toString() );
        existingAnimal.setName(animal.getName());
        existingAnimal.setSoundUrl(animal.getSoundUrl());
        existingAnimal.setPictureUrl(animal.getPictureUrl());

        return animalRepository.save(existingAnimal);

    }

    public Animal updateAnimalPicture(Long id, String newPictureUrl) {
        Animal existingAnimal = animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException("Animal not found with id: "
                + id));

        existingAnimal.setPictureUrl(newPictureUrl);
        return animalRepository.save(existingAnimal);
    }

    public Animal updateAnimalSound(Long id, String newSoundUrl) {
        Animal existingAnimal = animalRepository.findById(id).orElseThrow(() -> new AnimalNotFoundException("Animal not found with id: " + id));

        existingAnimal.setSoundUrl(newSoundUrl);
        return animalRepository.save(existingAnimal);
    }

    public void deleteAnimal(long id) {
        if (!animalRepository.existsById(id)) {
            throw new AnimalNotFoundException("Animal not found id: " + id);
        }
        animalRepository.deleteById(id);
    }
}

