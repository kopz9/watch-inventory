package me.kopz.watch.dto;

import java.util.UUID;

public record WatchDTO(
    UUID id,
    String brand,
    String model,
    String reference,
    String movementType,
    String boxMaterial,
    String glassType,
    int waterResistanceM,
    int diameterMm,
    int lugToLugMm,
    int thicknessMm,
    int widthMm,
    long priceInCents,
    String imageUrl,
    String bandWaterResistant,
    int collectionRating
) {
}
