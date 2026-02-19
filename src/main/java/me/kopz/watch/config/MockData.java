package me.kopz.watch.config;

import lombok.RequiredArgsConstructor;
import me.kopz.watch.entity.Watch;
import me.kopz.watch.entity.enums.BoxMaterial;
import me.kopz.watch.entity.enums.GlassType;
import me.kopz.watch.entity.enums.MovementType;
import me.kopz.watch.repository.WatchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class MockData {

  private final WatchRepository watchRepository;

  @Bean
  CommandLineRunner mockData() {
    return args -> {
      if (watchRepository.count() > 0) return;

      var now = Instant.now();

      List<Watch> watches = List.of(
          Watch.builder()
              .id(UUID.randomUUID())
              .brand("Casio")
              .model("F-91W")
              .movementType(MovementType.QUARTZ)
              .boxMaterial(BoxMaterial.RESIN)
              .glassType(GlassType.ACRYLIC)
              .waterResistanceM(30)
              .diameterMm(35)
              .lugToLugMm(38)
              .thicknessMm(9)
              .width(18)
              .priceInCents(12990)
              .imageUrl("https://picsum.photos/seed/relogio1/800/800")
              .createdAt(now.minusSeconds(50000))
              .build(),

          Watch.builder()
              .id(UUID.randomUUID())
              .brand("Seiko")
              .model("Diver 200m")
              .movementType(MovementType.AUTOMATIC)
              .boxMaterial(BoxMaterial.STEEL)
              .glassType(GlassType.MINERAL)
              .waterResistanceM(200)
              .diameterMm(42)
              .lugToLugMm(46)
              .thicknessMm(13)
              .width(22)
              .priceInCents(159990)
              .imageUrl("https://picsum.photos/seed/relogio2/800/800")
              .createdAt(now.minusSeconds(30000))
              .build(),

          Watch.builder()
              .id(UUID.randomUUID())
              .brand("Citizen")
              .model("Eco-Drive Field")
              .movementType(MovementType.QUARTZ)
              .boxMaterial(BoxMaterial.STEEL)
              .glassType(GlassType.MINERAL)
              .waterResistanceM(100)
              .diameterMm(37)
              .lugToLugMm(47)
              .thicknessMm(11)
              .width(18)
              .priceInCents(99990)
              .imageUrl("https://picsum.photos/seed/relogio3/800/800")
              .createdAt(now.minusSeconds(10000))
              .build()
      );
      watchRepository.saveAll(watches);
    };
  }
}
