package me.kopz.watch.entity.enums;

public enum GlassType {
  MINERAL, SAPPHIRE, ACRYLIC;

  public static GlassType fromApi(String value) {
    if (value == null || value.isBlank()) return null;
    return valueOf(value.toUpperCase());
  }

  public String toApi() {
    return name().toLowerCase();
  }
}
