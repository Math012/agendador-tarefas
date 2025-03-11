package com.math012.agendadortarefas.business;

import com.math012.agendadortarefas.business.dto.TarefasDTO;
import com.math012.agendadortarefas.business.mapper.TarefaConverter;
import com.math012.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.math012.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.math012.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.math012.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.math012.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.math012.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.math012.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum.*;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO tarefasDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(PENDENTE);
        tarefasDTO.setEmailUsuario(email);
        return tarefaConverter.tarefaEntityParaTarefaDTO(
                tarefasRepository.save(
                        tarefaConverter.dtoParaTarefaEntity(tarefasDTO)
                )
        );
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriado(LocalDateTime dataIncial, LocalDateTime dataFinal) {
        return tarefaConverter.listaTarefaEntityParaListaTarefaDTO(tarefasRepository.findByDataEventoBetween(dataIncial, dataFinal));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefaConverter.listaTarefaEntityParaListaTarefaDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletarTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar o id: " + id);
        }
    }

    public TarefasDTO alterarStatus(StatusNotificacaoEnum statusNotificacaoEnum, String id){
        try {
            TarefasEntity tarefas = tarefasRepository.findById(id).orElseThrow(()->
                    new ResourceNotFoundException("Tarefa não encontrada!"));
            tarefas.setStatusNotificacaoEnum(statusNotificacaoEnum);
            return tarefaConverter.tarefaEntityParaTarefaDTO(tarefasRepository.save(tarefas));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa " + e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO tarefasDTO, String id){
        try{
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(()->
                    new ResourceNotFoundException("Tarefa não encontrada!"));

            tarefaUpdateConverter.updateTarefa(tarefasDTO,entity);
            return tarefaConverter.tarefaEntityParaTarefaDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao atualizar a tarefa " + e.getCause());
        }
    }
}
