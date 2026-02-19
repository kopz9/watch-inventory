package me.kopz.watch.entity.enums;

public enum BoxMaterial {
  STEEL, TITANIUM, RESIN, BRONZE, CERAMIC;

  public static BoxMaterial fromApi(String value) {
    if (value == null || value.isBlank()) return null;
    return valueOf(value.toUpperCase());
  }

  public String toApi() {
    return name().toLowerCase();
  }
}
