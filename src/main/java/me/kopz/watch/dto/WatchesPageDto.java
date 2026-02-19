package me.kopz.watch.dto;

import java.util.List;

public record WatchesPageDto(
    List<WatchDTO> items,
    long total
) {
}
