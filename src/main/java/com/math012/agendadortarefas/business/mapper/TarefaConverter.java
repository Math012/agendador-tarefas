package com.math012.agendadortarefas.business.mapper;

import com.math012.agendadortarefas.business.dto.TarefasDTO;
import com.math012.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefasEntity dtoParaTarefaEntity(TarefasDTO tarefasDTO);
    TarefasDTO tarefaEntityParaTarefaDTO(TarefasEntity tarefas);
}
