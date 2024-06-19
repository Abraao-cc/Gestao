package com.timuila.gestao.services.imp;

import com.timuila.gestao.domain.Departamento;
import com.timuila.gestao.dtos.DepartamentoDTO;
import com.timuila.gestao.repositorys.DepartamentoRepository;
import com.timuila.gestao.services.DepartamentoService;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrativo
 */
@Service
public class DepartamentoServiceImp implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImp(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public Departamento save(DepartamentoDTO departamentoDTO) {
     Departamento centroCusto = this.toEntity(departamentoDTO);

        validarAtributos(centroCusto);
        if (departamentoDTO.id() == null) {
            return departamentoRepository.save(centroCusto);
        }

        return update(departamentoDTO);
    }
    
     private Departamento update(DepartamentoDTO departamentoDTO) {
        Departamento departamento = departamentoRepository.findById(departamentoDTO.id()).get();
        departamento.setNome(departamentoDTO.nome());
        departamento.setId(departamentoDTO.id());
        return departamentoRepository.save(departamento);
    }

    @Override
    public Map<UUID, String> cargos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Departamento findById(UUID id) {
       return departamentoRepository.findById(id).get();
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Departamento findByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Departamento> getDepartamentos() {
        return departamentoRepository.findAll(Sort.by("id"));
    }
 private void validarAtributos(Departamento d) {
        Optional<Departamento> departamento = departamentoRepository.findByNome(d.getNome());
        if (departamento.isPresent() && !Objects.equals(departamento.get().getId(), d.getId())) {
            throw new DataIntegrityViolationException("nome já cadastro no sistema!");
        }

    }
    protected DepartamentoDTO toDTO(Departamento departamento) {

        return new DepartamentoDTO(departamento.getId(), departamento.getNome());
    }

    protected Departamento toEntity(DepartamentoDTO dto) {
        Departamento departamento = new Departamento();
        departamento.setId(dto.id());
        departamento.setNome(dto.nome());
        return departamento;
    }

}
