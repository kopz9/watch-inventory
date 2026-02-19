package me.kopz.watch.service.enums;

public enum WatchSorting {
  NEWEST,
  PRICE_ASC,
  PRICE_DESC,
  DIAMETER_ASC,
  WATER_RESISTANCE_DESC;

  public static WatchSorting fromApi(String value) {
    if (value == null || value.isBlank()) return NEWEST;
    return switch (value) {
      case "newest" -> NEWEST;
      case "price_asc" -> PRICE_ASC;
      case "price_desc" -> PRICE_DESC;
      case "diameter_asc" -> DIAMETER_ASC;
      case "wr_desc" -> WATER_RESISTANCE_DESC;
      default -> throw new IllegalArgumentException("Invalid sorting: " + value);
    };
  }
}
