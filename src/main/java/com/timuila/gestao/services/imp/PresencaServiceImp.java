
package com.timuila.gestao.services.imp;

import com.timuila.gestao.domain.Funcionario;
import com.timuila.gestao.domain.Presenca;
import com.timuila.gestao.dtos.PresencaDTO;
import com.timuila.gestao.repositorys.PresencaRepository;
import com.timuila.gestao.services.PresencaService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrativo
 */
@Service
public class PresencaServiceImp implements PresencaService {
    private final PresencaRepository presencaRepository;

    public PresencaServiceImp(PresencaRepository presencaRepository) {
        this.presencaRepository = presencaRepository;
    }

   @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void salvar(PresencaDTO dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void baterPonto(UUID funcionarioId, LocalTime horaAtual) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PresencaDTO buscarPorId(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PresencaDTO> getPresencas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
