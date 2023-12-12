package com.example.demo.service;

import com.example.demo.model.Animal;
import com.example.demo.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("/application-test.properties")
class AnimalServiceTest {
    @InjectMocks
    private AnimalService service;
    @Mock
    private AnimalRepository repository;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveAnimal() {
        Animal animal= new Animal("Dog","Wow.mp3", "Denny");

        when(repository.save(any(Animal.class))).thenReturn(animal);

        Animal saveAnimal= repository.save(animal);

        assertEquals(animal.getName(),saveAnimal.getName());
        assertEquals(animal.getPictureUrl(),saveAnimal.getPictureUrl());
        assertEquals(animal.getSoundUrl(),saveAnimal.getSoundUrl());
    }
    @Test
    void updateAnimal() {
        Animal existingAnimal = new Animal("Dog", "Wow.mp3", "Denny");
        existingAnimal.setId(1L);
        Animal updatedAnimal = new Animal("Cat", "Mao.mp3", "Baza");
        updatedAnimal.setId(1L);

        System.out.println("Existing Animal ID: " + existingAnimal.getId());
        System.out.println("Updated Animal ID: " + updatedAnimal.getId());

        when(repository.findById(1L)).thenReturn(Optional.of(existingAnimal));


        assertTrue(repository.findById(1L).isPresent());
        assertEquals("Dog", repository.findById(1L).get().getName());

        Animal result = service.updateAnimal(updatedAnimal);

        assertEquals(updatedAnimal.getName(), result.getName());
        assertEquals(updatedAnimal.getSoundUrl(), result.getSoundUrl());
        assertEquals(updatedAnimal.getPictureUrl(), result.getPictureUrl());
    }

    @Test
    void updateAnimalPicture() {
    }

    @Test
    void updateAnimalSound() {
    }

    @Test
    void deleteAnimal() {
    }
}