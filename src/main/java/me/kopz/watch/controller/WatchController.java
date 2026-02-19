package me.kopz.watch.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.kopz.watch.dto.CreateWatchRequest;
import me.kopz.watch.dto.UpdateWatchRequest;
import me.kopz.watch.dto.WatchDTO;
import me.kopz.watch.dto.WatchesPageDto;
import me.kopz.watch.service.WatchService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/watches")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // not for production
public class WatchController {

  private final WatchService service;

  @GetMapping
  public WatchesPageDto list(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "12") int perPage,
      @RequestParam(required = false) String search,
      @RequestParam(required = false) String brand,
      @RequestParam(required = false) String movementType,
      @RequestParam(required = false) String boxMaterial,
      @RequestParam(required = false) String glassType,
      @RequestParam(required = false) Integer resistanceMin,
      @RequestParam(required = false) Integer resistanceMax,
      @RequestParam(required = false) Long priceMin,
      @RequestParam(required = false) Long priceMax,
      @RequestParam(required = false) Integer diameterMin,
      @RequestParam(required = false) Integer diameterMax,
      @RequestParam(required = false) String sort
  ) {
    return service.list(
        page, perPage, search, brand, movementType, boxMaterial, glassType, resistanceMin, resistanceMax, priceMin, priceMax, diameterMin, diameterMax, sort
    );
  }

  @GetMapping("/{id}")
  public WatchDTO search(@PathVariable UUID id) {
    return service.findById(id);
  }

  @PostMapping
  public WatchDTO create(@Valid @RequestBody CreateWatchRequest req) {
    return service.create(req);
  }

  @PutMapping("{/id}")
  public WatchDTO update(@PathVariable UUID id, @Valid @RequestBody UpdateWatchRequest req) {
    return service.update(id, req);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }

}
