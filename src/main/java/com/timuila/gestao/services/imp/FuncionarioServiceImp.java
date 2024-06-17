package com.timuila.gestao.services.imp;

import br.ind.cmil.gestao.exceptions.FuncionarioException;
import com.timuila.gestao.datatables.Datatables;
import com.timuila.gestao.datatables.DatatablesColunas;
import com.timuila.gestao.dominio.Cargo;
import com.timuila.gestao.dominio.CentroCusto;
import com.timuila.gestao.dominio.Departamento;
import com.timuila.gestao.dominio.Funcionario;
import com.timuila.gestao.dtos.FuncionarioRecord;
import com.timuila.gestao.emuns.EstadoCivil;
import com.timuila.gestao.emuns.Genero;
import com.timuila.gestao.repositorys.FuncionarioRepository;
import com.timuila.gestao.services.FuncionarioService;
import com.timuila.gestao.util.CustomCollectors;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrativo
 */
@Service
public class FuncionarioServiceImp implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final Datatables datatables;
   // private final DepartamentoRepository departamentoRepository;
    //private final CargoRepository cargoRepository;
   // private final CentroCustoRepository centroCustoRepository;

    public FuncionarioServiceImp(FuncionarioRepository funcionarioRepository, Datatables datatables) {
        this.funcionarioRepository = funcionarioRepository;
        this.datatables = datatables;
    }

  

    @Override
    @Transactional(readOnly = true)
    public List<FuncionarioRecord> list() {

        List<Funcionario> funcionarios = funcionarioRepository.findAll(Sort.by("id"));
        return funcionarios.stream().map(this::toDTO).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public Map<UUID, String> funcionarios() {

        return funcionarioRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Funcionario::getId, Funcionario::getNome));

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public UUID save(FuncionarioRecord funcionarioDTO) {
        Funcionario funcionario = this.toEntity(funcionarioDTO);
        validarAtributos(funcionario);
        if (funcionarioDTO.id() == null) {
            return funcionarioRepository.save(funcionario).getId();
        }

        return update(funcionarioDTO).id();

    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    protected FuncionarioRecord update(FuncionarioRecord dto) {
        Funcionario funcionario = funcionarioRepository.findById(dto.id()).get();
        funcionario.setNome(dto.nome());
        funcionario.setId(dto.id());
        funcionario.setNome(dto.nome());
        funcionario.setSobrenome(dto.sobrenome());
        funcionario.setNascimento(dto.nascimento());
        funcionario.setCpf(dto.cpf());
        funcionario.setRg(dto.rg());
        funcionario.setMae(dto.mae());
        funcionario.setPai(dto.pai());
        funcionario.setClt(dto.clt());
        funcionario.setGenero(Genero.convertGeneroValue(dto.genero()));
        funcionario.setEstado_civil(EstadoCivil.findTipo(dto.estado_civil()));
        funcionario.setNaturalidade(dto.naturalidade());
        LocalDate data = (funcionario.getAdmissao()) == null ? LocalDate.now() : funcionario.getAdmissao();
        funcionario.setAdmissao(data);
        funcionario.setDemissao(null);
        funcionario.setCargo(new Cargo(dto.cargo()));
        funcionario.setDepartamento(new Departamento(dto.departamento()));
        funcionario.setCentro(new CentroCusto(dto.centro()));
        return this.toDTO(funcionarioRepository.save(funcionario));

    }

    @Override
    @Transactional(readOnly = true)
    public FuncionarioRecord buscarFuncionarioPorId(UUID id) {
        return funcionarioRepository.findById(id).map(funcionario -> this.toDTO(funcionario)).get();
    }

    @Override
    @Transactional(readOnly = true)
    public FuncionarioRecord buscarFuncionarioPorNome(String nome) {

        return funcionarioRepository.findByClt(nome).map(funcionario -> this.toDTO(funcionario)).get();
    }

    @Transactional(readOnly = false)
    public void demitirFuncionario(UUID id) {
        Funcionario fu = funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! "));
        fu.setDemissao(LocalDate.now());
    }

    @Override
    public void delete(UUID id) {
        funcionarioRepository.delete(funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioException(String.valueOf(id), "Este id não consta no bd! ")));
    }

    private void validarAtributos(Funcionario f) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByClt(f.getClt());
        if (funcionario.isPresent() && !Objects.equals(funcionario.get().getId(), f.getId())) {
            throw new DataIntegrityViolationException("clt já cadastro no sistema!");
        }

    }

    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.FUNCIONARIO);
        Page<?> page = datatables.getSearch().isEmpty() ? funcionarioRepository.findAll(datatables.getPageable())
                : funcionarioRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public long countById() {
        return funcionarioRepository.count();
    }

    protected LocalDate data(LocalDate dto) {
        return (dto == null ? LocalDate.now() : dto);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Funcionario> funcionarioString(Set<String> funcionarosString) {
        Set<Funcionario> funcionarios = new HashSet<>();

        for (String string : funcionarosString) {
            Funcionario funcionario = funcionarioRepository.findByClt(string).get();
            funcionarios.add(funcionario);

        }
        return funcionarios;

    }

    protected FuncionarioRecord toDTO(Funcionario funcionario) {
        UUID cargo = (funcionario.getCargo().getId() == null) ? null : funcionario.getCargo().getId();
        UUID departamento = (funcionario.getDepartamento().getId() == null) ? null : funcionario.getDepartamento().getId();
        UUID centro = (funcionario.getCentro().getId() == null) ? null : funcionario.getCentro().getId();
        return new FuncionarioRecord(funcionario.getId(), funcionario.getNome(), funcionario.getSobrenome(), funcionario.getNascimento(), funcionario.getCpf(), funcionario.getRg(), funcionario.getMae(), funcionario.getPai(), funcionario.getGenero().getValue(), funcionario.getEstado_civil().getValue(), funcionario.getNaturalidade(), funcionario.getClt(), cargo, departamento, centro);

    }

    protected Funcionario toEntity(FuncionarioRecord dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.id());
        funcionario.setNome(dto.nome());
        funcionario.setSobrenome(dto.sobrenome());
        funcionario.setNascimento(dto.nascimento());
        funcionario.setCpf(dto.cpf());
        funcionario.setRg(dto.rg());
        funcionario.setMae(dto.mae());
        funcionario.setPai(dto.pai());
        funcionario.setClt(dto.clt());
        funcionario.setGenero(Genero.convertGeneroValue(dto.genero()));
        funcionario.setEstado_civil(EstadoCivil.findTipo(dto.estado_civil()));
        funcionario.setNaturalidade(dto.naturalidade());
        LocalDate data = (funcionario.getAdmissao()) == null ? LocalDate.now() : funcionario.getAdmissao();
        funcionario.setAdmissao(data);
        funcionario.setDemissao(null);
        funcionario.setCargo(new Cargo(dto.cargo()));
        funcionario.setDepartamento(new Departamento(dto.departamento()));
        funcionario.setCentro(new CentroCusto(dto.centro()));

        return funcionario;
    }

}
