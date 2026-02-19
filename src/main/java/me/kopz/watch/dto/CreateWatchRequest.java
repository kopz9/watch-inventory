package me.kopz.watch.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateWatchRequest(
    @NotBlank @Size(max = 80) String brand,
    @NotBlank @Size(max = 120) String model,
    @NotBlank @Size(max = 80) String reference,

    @NotBlank String movementType,
    @NotBlank String boxMaterial,
    @NotBlank String glassType,

    @Min(0) int waterResistanceM,

    @Min(20)int diameterMm,
    @Min(20) int lugToLugMm,
    @Min(5) int thicknessMm,
    @Min(10) int widthLugMm,

    @Min(1) long priceInCents,

    @NotBlank @Size(max = 600) String imageUrl






    ) {
}
