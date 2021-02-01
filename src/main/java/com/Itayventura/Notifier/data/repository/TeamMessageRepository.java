package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.data.entity.TeamMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMessageRepository extends MessageRepository<TeamMessage> {

    Iterable<TeamMessage> findAllByTeam(Team team);
}
