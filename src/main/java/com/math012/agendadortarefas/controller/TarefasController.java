package com.math012.agendadortarefas.controller;

import com.math012.agendadortarefas.business.TarefasService;
import com.math012.agendadortarefas.business.dto.TarefasDTO;
import com.math012.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                   @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token,tarefasDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscaListaDeTarefaPorPeriado(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal
            ){
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriado(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(
            @RequestHeader("Authorization") String token
    ){
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id){
        tarefasService.deletarTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStatusNotificacao(@RequestParam("status")StatusNotificacaoEnum statusNotificacaoEnum,
                                                              @RequestParam("id")String id){
        return ResponseEntity.ok(tarefasService.alterarStatus(statusNotificacaoEnum,id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefa(@RequestBody TarefasDTO tarefasDTO, @RequestParam("id")String id){
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefasDTO, id));
    }


}
