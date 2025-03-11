package com.math012.agendadortarefas.business.mapper;

import com.math012.agendadortarefas.business.dto.TarefasDTO;
import com.math012.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefa(TarefasDTO tarefasDTO, @MappingTarget TarefasEntity tarefas);
}
