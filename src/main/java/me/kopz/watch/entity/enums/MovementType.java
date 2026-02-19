package me.kopz.watch.entity.enums;

public enum MovementType {
  QUARTZ, AUTOMATIC, MANUAL;

  public static MovementType fromApi(final String value) {
    if (value == null || value.isBlank()) return null;
    return valueOf(value.toUpperCase());
  }

  public String toApi() {
    return name().toLowerCase();
  }
}
