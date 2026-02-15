package me.kopz.watch.dto;

public record WatchsPageDto(
    List<WatchDTO> items,
    long total
) {
}
