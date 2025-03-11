package com.math012.agendadortarefas.infrastructure.repository;

import com.math012.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {
}
