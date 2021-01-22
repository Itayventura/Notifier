package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
    Optional<Team> findOneByName(String name);
}
