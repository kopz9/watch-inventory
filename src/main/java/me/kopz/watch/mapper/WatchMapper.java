package me.kopz.watch.mapper;

import me.kopz.watch.dto.WatchDTO;
import me.kopz.watch.entity.Watch;
import me.kopz.watch.entity.enums.BoxMaterial;
import me.kopz.watch.entity.enums.GlassType;
import me.kopz.watch.entity.enums.MovementType;
import org.springframework.stereotype.Component;

@Component
public class WatchMapper {

  public WatchDTO toDTO(Watch w) {
    return WatchDTO.builder()
        .id(w.getId())
        .brand(w.getBrand())
        .model(w.getModel())
        .reference(w.getReference())
        .movementType(w.getMovementType().toApi())
        .boxMaterial(w.getBoxMaterial().toApi())
        .glassType(w.getGlassType().toApi())
        .waterResistanceM(w.getWaterResistanceM())
        .diameterMm(w.getDiameterMm())
        .lugToLugMm(w.getLugToLugMm())
        .thicknessMm(w.getThicknessMm())
        .widthMm(w.getWidth())
        .priceInCents(w.getPriceInCents())
        .imageUrl(w.getImageUrl())
        .bandWaterResistant(bandResistance(w.getWaterResistanceM()))
        .collectionRating(collectionScore(w))
        .build();
  }

  private String bandResistance(int resistanceM) {
    if (resistanceM < 50) return "splash";
    if (resistanceM < 100) return "daily_use";
    if (resistanceM < 200) return "swimming";
    return "diving";
  }

  private int collectionScore(Watch w) {
    int points = 0;

    if (w.getGlassType() == GlassType.SAPPHIRE) points += 25;

    if (w.getWaterResistanceM() >= 100) points += 15;
    if (w.getWaterResistanceM() >= 200) points += 10;

    if (w.getMovementType() == MovementType.AUTOMATIC) points += 20;

    if (w.getBoxMaterial() == BoxMaterial.STEEL) points += 10;
    if (w.getBoxMaterial() == BoxMaterial.TITANIUM) points += 12;

    if (w.getDiameterMm() >= 30 && w.getDiameterMm() <= 42) points += 8;

    return points;
  }
}
