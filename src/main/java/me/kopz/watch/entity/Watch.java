package me.kopz.watch.entity;

import jakarta.persistence.*;
import lombok.*;
import me.kopz.watch.entity.enums.BoxMaterial;
import me.kopz.watch.entity.enums.GlassType;
import me.kopz.watch.entity.enums.MovementType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Watch {

  @Id
  @Column(nullable = false, updatable = false)
  private UUID id;

  @Column(nullable = false, length = 80)
  private String brand;

  @Column(nullable = false, length = 120)
  private String model;

  @Column(nullable = false, length = 80)
  private String reference;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private MovementType movementType;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private BoxMaterial boxMaterial;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private GlassType glassType;

  @Column(nullable = false)
  private int waterResistanceM;

  @Column(nullable = false)
  private int diameterMm;

  @Column(nullable = false)
  private int lugToLugMm;

  @Column(nullable = false)
  private int thicknessMm;

  @Column(nullable = false)
  private int width;

  @Column(nullable = false)
  private long priceInCents;

  @Column(nullable = false, length = 600)
  private String urlImage;

  @Column(nullable = false)
  private Instant createdAt;

  @PrePersist
  void prePersist() {
    if (id == null) id = UUID.randomUUID();
    if (createdAt == null) createdAt = Instant.now();
    normalize();
  }

  @PreUpdate
  void preUpdate() {
    normalize();
  }

  private void normalize() {
    if (brand != null) brand = brand.trim();
    if (model != null) model = model.trim();
    if (reference != null) reference = reference.trim();
    if (urlImage != null) urlImage = urlImage.trim();
  }

}
