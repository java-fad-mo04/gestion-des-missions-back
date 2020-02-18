package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.domain.Version;

@Repository
public interface VersionRepo extends JpaRepository<Version, Integer> {
}
