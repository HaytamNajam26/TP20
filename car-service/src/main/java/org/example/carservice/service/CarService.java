package org.example.carservice.service;

import lombok.RequiredArgsConstructor;
import org.example.carservice.models.Car;
import org.example.carservice.models.CarResponse;
import org.example.carservice.models.Client;
import org.example.carservice.repositories.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepo;
    private final RestTemplate restTemplate;

    private static final String CLIENT_URL = "http://localhost:8888/SERVICE-CLIENT/api/clients/";

    public List<CarResponse> findAll() {
        return carRepo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public CarResponse findById(Long id) throws Exception {
        Car car = carRepo.findById(id).orElseThrow();
        return toResponse(car);
    }

    public Car save(Car car) { return carRepo.save(car); }

    private CarResponse toResponse(Car car) {
        Client client = null;
        try {
            client = restTemplate.getForObject(CLIENT_URL + car.getClient_id(), Client.class);
        } catch (Exception e) {  client = null; }

        return CarResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .matricule(car.getMatricule())
                .client(client)
                .build();
    }
}
