package com.math012.agendadortarefas.business.mapper;

import com.math012.agendadortarefas.business.dto.TarefasDTO;
import com.math012.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefasEntity dtoParaTarefaEntity(TarefasDTO tarefasDTO);
    TarefasDTO tarefaEntityParaTarefaDTO(TarefasEntity tarefas);
    List<TarefasEntity> listaTarefaDTOParaListaTarefaEntity(List<TarefasDTO> tarefasDTOList);
    List<TarefasDTO> listaTarefaEntityParaListaTarefaDTO(List<TarefasEntity> tarefasEntities);
}
