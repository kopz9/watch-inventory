package me.kopz.watch.service.specification;

import me.kopz.watch.entity.Watch;
import me.kopz.watch.entity.enums.BoxMaterial;
import me.kopz.watch.entity.enums.GlassType;
import me.kopz.watch.entity.enums.MovementType;
import org.springframework.data.jpa.domain.Specification;

public final class WatchSpecs {

  private WatchSpecs() {
  }

  public static Specification<Watch> all() {
    return (root, q, cb) -> cb.conjunction();
  }

  private static boolean blank(String s) {
    return s == null || s.isBlank();
  }

//
//  WHERE
//  LOWER(brand)         LIKE '%term%'
//  OR LOWER(model)      LIKE '%term%'
//  OR LOWER(reference)  LIKE '%term%'

  public static Specification<Watch> search(String term) {
    if (blank(term)) return all();
    String like = "%" + term.trim().toLowerCase() + "%";
    return (root, q, cb) -> cb.or(
        cb.like(cb.lower(root.get("brand")), like),
        cb.like(cb.lower(root.get("model")), like),
        cb.like(cb.lower(root.get("reference")), like)
    );
  }

  public static Specification<Watch> brandEquals(String brand) {
    if (blank(brand)) return all();
    String v = brand.trim().toLowerCase();
    return (root, q, cb) -> cb.equal(cb.lower(root.get(brand)), v);
  }

  public static Specification<Watch> movementTypeEquals(MovementType movementType) {
    if (movementType == null) return all();
    return (root, q, cb) -> cb.equal(root.get("movementType"), movementType);
  }

  public static Specification<Watch> boxMaterialEquals(BoxMaterial material) {
    if (material == null) return all();
    return (root, q, cb) -> cb.equal(root.get("boxMaterial"), material);
  }

  public static Specification<Watch> glassTypeMaterial(GlassType glassType) {
    if (glassType == null) return all();
    return (root, q, cb) -> cb.equal(root.get("glassType"), glassType);
  }

  public static Specification<Watch> waterResistanceBetween(Integer min, Integer max) {
    if (min == null && max == null) return all();
    return (root, q, cb) -> {
      if (min != null && max != null) return cb.between(root.get("waterResistanceM"), min, max);
      if (min != null) return cb.greaterThanOrEqualTo(root.get("waterResistanceM"), min);
      return cb.lessThanOrEqualTo(root.get("waterResistanceM"), max);
    };
  }

  public static Specification<Watch> priceBetween(Long min, Long max) {
    if (min == null && max == null) return all();
    return (root, q, cb) -> {
      if (min != null && max != null) return cb.between(root.get("priceInCents"), min, max);
      if (min != null) return cb.greaterThanOrEqualTo(root.get("priceInCents"), min);
      return cb.lessThanOrEqualTo(root.get("priceInCents"), max);
    };
  }

  public static Specification<Watch> diameterBetween(Integer min, Integer max) {
    if (min == null && max == null) return all();
    return (root, q, cb) -> {
      if (min != null && max != null) return cb.between(root.get("diameterMm"), min, max);
      if (min != null) return cb.greaterThanOrEqualTo(root.get("diameterMm"), min);
      return cb.lessThanOrEqualTo(root.get("diameterMm"), max);
    };
  }
}