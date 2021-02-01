package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MessageRepository<T extends Message> extends CrudRepository<T, Integer> {

}
