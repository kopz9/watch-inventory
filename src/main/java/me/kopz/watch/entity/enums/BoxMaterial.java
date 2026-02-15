package me.kopz.watch.entity.enums;

public enum BoxMaterial {
  STEEL, TITANIUM, RESIN, BRONZE, CERAMIC;

  public static BoxMaterial fromApi(String value) {
    if (value == null || value.isBlank()) return null;
    return switch (value) {
      case "steel" -> STEEL;
      case "titanium" -> TITANIUM;
      case "resin" -> RESIN;
      case "bronze" -> BRONZE;
      case "ceramic" -> CERAMIC;
      default -> throw new IllegalArgumentException("Box material invalid: " + value);
    };
  }

  public String toApi() {
    return switch (this) {
      case STEEL -> "steel";
      case TITANIUM -> "titanium";
      case RESIN -> "resin";
      case BRONZE -> "bronze";
      case CERAMIC -> "ceramic";
    };
  }

}
