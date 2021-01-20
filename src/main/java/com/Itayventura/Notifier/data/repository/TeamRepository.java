package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Optional<Team> findOneByName(String name);
}
