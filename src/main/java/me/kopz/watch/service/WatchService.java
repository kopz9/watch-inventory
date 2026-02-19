package me.kopz.watch.service;

import lombok.RequiredArgsConstructor;
import me.kopz.watch.dto.CreateWatchRequest;
import me.kopz.watch.dto.UpdateWatchRequest;
import me.kopz.watch.dto.WatchDTO;
import me.kopz.watch.dto.WatchesPageDto;
import me.kopz.watch.entity.Watch;
import me.kopz.watch.entity.enums.BoxMaterial;
import me.kopz.watch.entity.enums.GlassType;
import me.kopz.watch.entity.enums.MovementType;
import me.kopz.watch.exception.NotFoundException;
import me.kopz.watch.mapper.WatchMapper;
import me.kopz.watch.repository.WatchRepository;
import me.kopz.watch.service.enums.WatchSorting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

import static me.kopz.watch.service.specification.WatchSpecs.*;


@Service
@RequiredArgsConstructor
public class WatchService {

  private final WatchRepository repository;
  private final WatchMapper mapper;

  public WatchesPageDto list(
      int page,
      int perPage,
      String search,
      String brand,
      String movementType,
      String boxMaterial,
      String glassType,
      Integer resistanceMin,
      Integer resistanceMax,
      Long priceMin,
      Long priceMax,
      Integer diameterMin,
      Integer diameterMax,
      String sort
  ) {
    int safePage = Math.max(1, page);
    int perPageSafe = Math.min(60, Math.max(1, perPage));

    MovementType movement = MovementType.fromApi(movementType);
    BoxMaterial material = BoxMaterial.fromApi(boxMaterial);
    GlassType glass = GlassType.fromApi(glassType);

    WatchSorting sorting = WatchSorting.fromApi(sort);

    Sort sort_ = switch (sorting) {
      case NEWEST -> Sort.by(Sort.Direction.DESC, "createdAt");
      case PRICE_ASC -> Sort.by(Sort.Direction.ASC, "priceInCents");
      case PRICE_DESC -> Sort.by(Sort.Direction.DESC, "priceInCents");
      case DIAMETER_ASC -> Sort.by(Sort.Direction.ASC, "diameterMm");
      case WATER_RESISTANCE_DESC -> Sort.by(Sort.Direction.DESC, "waterResistanceMm");
    };

    Pageable pageable = PageRequest.of(safePage - 1, perPageSafe, sort_);

    Specification<Watch> spec = Specification.where(search(search))
        .and(brandEquals(brand))
        .and(movementTypeEquals(movement))
        .and(boxMaterialEquals(material))
        .and(glassTypeMaterial(glass))
        .and(waterResistanceBetween(resistanceMin, resistanceMax))
        .and(priceBetween(priceMin, priceMax))
        .and(diameterBetween(diameterMin, diameterMax));

    Page<Watch> result = repository.findAll(spec, pageable);

    return new WatchesPageDto(
        result.getContent().stream().map(mapper::toDTO).toList(),
        result.getTotalElements()
    );
  }

  public WatchDTO findById(UUID id) {
    Watch w = repository.findById(id).orElseThrow(() -> new NotFoundException("Watch not found: " + id));
    return mapper.toDTO(w);
  }

  public WatchDTO create(CreateWatchRequest req) {
    Watch w = Watch.builder()
        .id(UUID.randomUUID())
        .brand(req.brand())
        .model(req.model())
        .reference(req.reference())
        .movementType(MovementType.fromApi(req.movementType()))
        .boxMaterial(BoxMaterial.fromApi(req.boxMaterial()))
        .glassType(GlassType.fromApi(req.glassType()))
        .waterResistanceM(req.waterResistanceM())
        .diameterMm(req.diameterMm())
        .lugToLugMm(req.lugToLugMm())
        .priceInCents(req.priceInCents())
        .imageUrl(req.imageUrl())
        .createdAt(Instant.now())
        .build();

    Watch saved = repository.save(w);
    return mapper.toDTO(saved);
  }

  public WatchDTO update(UUID id, UpdateWatchRequest req) {
    Watch w = repository.findById(id).orElseThrow(() -> new NotFoundException("Watch not found: " + id));

    w.setBrand(req.brand());
    w.setModel(req.model());
    w.setReference(req.reference());
    w.setMovementType(MovementType.fromApi(req.movementType()));
    w.setBoxMaterial(BoxMaterial.fromApi(req.boxMaterial()));
    w.setGlassType(GlassType.fromApi(req.glassType()));
    w.setWaterResistanceM(req.waterResistanceM());
    w.setDiameterMm(req.diameterMm());
    w.setLugToLugMm(req.lugToLugMm());
    w.setPriceInCents(req.priceInCents());
    w.setImageUrl(req.imageUrl());

    Watch saved = repository.save(w);
    return mapper.toDTO(saved);
  }

  public void delete(UUID id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException("Watch not found: " + id);
    }
    repository.deleteById(id);
  }

}
