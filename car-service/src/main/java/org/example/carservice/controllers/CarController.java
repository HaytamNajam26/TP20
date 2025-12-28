package org.example.carservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.carservice.models.Car;
import org.example.carservice.models.CarResponse;
import org.example.carservice.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService service;

    @GetMapping
    public List<CarResponse> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public CarResponse findById(@PathVariable Long id) throws Exception {
        return service.findById(id);
    }

    @PostMapping
    public Car save(@RequestBody Car car) { return service.save(car); }
}