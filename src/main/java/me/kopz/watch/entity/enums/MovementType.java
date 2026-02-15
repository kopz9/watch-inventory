package me.kopz.watch.entity.enums;

public enum MovementType {
  QUARTZ, AUTOMATIC, MANUAL;

  public static MovementType fromApi(final String value) {
    if (value == null || value.isBlank()) return null;
    return switch (value) {
      case "quartz" -> QUARTZ;
      case "AUTOMATIC" -> AUTOMATIC;
      case "MANUAL" -> MANUAL;
      default -> throw new IllegalArgumentException("Movement type invalid: " + value);
    };
  }

  public String toApi() {
    return switch (this) {
      case QUARTZ -> "quartz";
      case AUTOMATIC -> "automatic";
      case MANUAL -> "manual";
    };
  }
}
