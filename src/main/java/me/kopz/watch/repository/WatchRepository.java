package me.kopz.watch.repository;

import me.kopz.watch.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface WatchRepository extends JpaRepository<Watch, UUID>, JpaSpecificationExecutor<Watch> {
}
