package com.timuila.gestao.services.imp;

import com.timuila.gestao.domain.Funcionario;
import com.timuila.gestao.domain.Presenca;
import com.timuila.gestao.dtos.PresencaDTO;
import com.timuila.gestao.repositorys.FuncionarioRepository;
import com.timuila.gestao.repositorys.PresencaRepository;
import com.timuila.gestao.services.PresencaService;
import com.timuila.gestao.util.NotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrativo
 */
@Service
public class PresencaServiceImp implements PresencaService {

    private final PresencaRepository presencaRepository;
    private final FuncionarioRepository funcionarioRepository;

    public PresencaServiceImp(PresencaRepository presencaRepository, FuncionarioRepository funcionarioRepository) {
        this.presencaRepository = presencaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Presenca save(PresencaDTO dto) {
        final Presenca presenca = this.toEntity(dto);
        if (presenca.getId() != null) {
            return update(dto);
        }
        validarAtributos(presenca);
        return presencaRepository.save(presenca);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private Presenca update(final PresencaDTO dto) {
        final Presenca frequencia = presencaRepository.findById(dto.id())
                .orElseThrow(NotFoundException::new);
        // mapToEntity(frequenciaDTO, frequencia);
        return presencaRepository.save(frequencia);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void baterPonto(UUID funcionarioId, LocalTime horaAtual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PresencaDTO buscarPorId(UUID id) {
        Presenca presenca = presencaRepository.findById(id).get();
        return this.toDTO(presenca);
    }

    @Override
    public List<PresencaDTO> getPresencas() {
        final List<Presenca> presencas = presencaRepository.findAll(Sort.by("id"));
        return presencas.stream()
                .map(presenca -> this.toDTO(presenca))
                .toList();
    }

    @Override
    public void delete(UUID id) {
        presencaRepository.delete(presencaRepository.findById(id).orElseThrow(() -> new NotFoundException("Este id não consta no bd! ")));
    }
    private void validarAtributos(Presenca request) {
        Optional<Presenca> frequencia = presencaRepository.findByEntrada(request.getEntrada());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getId(), request.getId()) && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("já bateu a entrada!");
        }
        frequencia = presencaRepository.findByIntervalo(request.getIntervalo());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("já bateu a saída manhã!");
        }
        frequencia = presencaRepository.findByRetorno(request.getRetorno());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("já bateu a entrada tarde!");
        }
        frequencia = presencaRepository.findBySaida(request.getSaida());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("já bateu a saída tarde!");
        }
        frequencia = presencaRepository.findFirstByFuncionario(request.getFuncionario());
        if (frequencia.isPresent() && !Objects.equals(frequencia.get().getFuncionario().getId(), request.getFuncionario().getId())) {
            throw new DataIntegrityViolationException("Frequência já cadastrado no sistema!");
        }
    }
    protected void ponto(Presenca presenca, LocalTime horaAtual) {
        if (presenca.getIntervalo() == null || presenca.getIntervalo().equals("")) {
            presencaRepository.updateIntervalo(horaAtual, presenca.getId());
            // return frequencia.getId();
        } else if (presenca.getRetorno() == null || presenca.getRetorno().equals("")) {
            presencaRepository.updateRetorno(horaAtual, presenca.getId());
            //return frequencia.getId();
        } else if (presenca.getSaida() == null || presenca.getSaida().equals("")) {
            presencaRepository.updateSaida(horaAtual, presenca.getId());
            //return frequencia.getId();
        }
    }

    protected Presenca buscarFrequenciaPorFuncionarioId(UUID funcionarioId) {
        return presencaRepository.findByFuncionarioId(funcionarioId).orElse(new Presenca());
    }
     protected void verificarFrequencia(UUID funcionarioId) {
        LocalTime horaAtual = LocalTime.now();
        Presenca presenca = presencaRepository.findByFuncionarioId(funcionarioId).orElse(new Presenca());     
       
        if (presenca.getEntrada() == null || presenca.getEntrada().equals("")) {
            presencaRepository.updateEntrada(horaAtual, presenca.getId());
            // return frequencia.getId();
        } else if (presenca.getIntervalo() == null || presenca.getIntervalo().equals("")) {
            presencaRepository.updateIntervalo(horaAtual, presenca.getId());
            // return frequencia.getId();
        } else if (presenca.getRetorno() == null || presenca.getRetorno().equals("")) {
            presencaRepository.updateRetorno(horaAtual, presenca.getId());
            //return frequencia.getId();
        } else if (presenca.getSaida() == null || presenca.getSaida().equals("")) {
            presencaRepository.updateSaida(horaAtual, presenca.getId());
            //return frequencia.getId();
        }
    }

    public PresencaDTO toDTO(Presenca presenca) {
        UUID funcionario = presenca.getFuncionario().getId();
        LocalDate dataAtual = (presenca.getData() == null) ? LocalDate.now() : presenca.getData();
        return new PresencaDTO(presenca.getId(), dataAtual, funcionario);
    }

    public Presenca toEntity(PresencaDTO dto) {

        Presenca presenca = new Presenca();
        presenca.setId(dto.id());
        presenca.setEntrada(null);
        presenca.setIntervalo(null);
        presenca.setRetorno(null);
        presenca.setSaida(null);
        presenca.setHoraAtual(null);
        presenca.setData(dto.dataAtual());
        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.funcionario());
        presenca.setFuncionario(funcionario);
        return presenca;
    }

}
