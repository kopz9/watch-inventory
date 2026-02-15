package me.kopz.watch.entity.enums;

public enum GlassType {
  MINERAL, SAPPHIRE, ACRYLIC;

  public static GlassType fromApi(String value) {
    if (value == null || value.isBlank()) return null;
    return switch (value) {
      case "mineral" -> MINERAL;
      case "sapphire" -> SAPPHIRE;
      case "acrylic" -> ACRYLIC;
      default -> throw new IllegalArgumentException("Glass type invalid: " + value);
    };
  }

  public String toApi() {
    return switch (this) {
      case MINERAL -> "mineral";
      case SAPPHIRE -> "sapphire";
      case ACRYLIC -> "acrylic";
    };
  }
}
