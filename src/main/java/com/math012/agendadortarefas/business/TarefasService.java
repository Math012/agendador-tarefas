package com.math012.agendadortarefas.business;

import com.math012.agendadortarefas.business.dto.TarefasDTO;
import com.math012.agendadortarefas.business.mapper.TarefaConverter;
import com.math012.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.math012.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.math012.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum.*;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO tarefasDTO){
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
}
