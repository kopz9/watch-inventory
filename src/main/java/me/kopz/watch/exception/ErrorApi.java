package me.kopz.watch.exception;

import java.time.Instant;
import java.util.List;

public record ErrorApi(
    Instant timestamp,
    int status,
    String error,
    String message,
    String path,
    List<PathError> pathErrors
) {
  public record PathError(String path, String message){}
}
