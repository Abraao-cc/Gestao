package com.timuila.gestao.services.imp;

import com.timuila.gestao.datatables.Datatables;
import com.timuila.gestao.datatables.DatatablesColunas;
import com.timuila.gestao.domain.Pessoa;
import com.timuila.gestao.domain.Telefone;
import com.timuila.gestao.dtos.TelefoneDTO;
import com.timuila.gestao.emuns.TipoTelefone;
import com.timuila.gestao.repositorys.PessoaRepository;
import com.timuila.gestao.repositorys.TelefoneRepository;
import com.timuila.gestao.services.TelefoneService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrativo
 */
@Service
public class TelefoneServiceImp implements TelefoneService {

    private final TelefoneRepository telefoneRepository;
    private final Datatables datatables;
    private final PessoaRepository pessoaService;

    public TelefoneServiceImp(TelefoneRepository tr, Datatables datatables, PessoaRepository pessoaService) {
        this.telefoneRepository = tr;
        this.datatables = datatables;
        this.pessoaService = pessoaService;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Telefone save(TelefoneDTO telefoneDTO) {
        if (telefoneDTO.id() == null) {
            Telefone telefone = this.toEntity(telefoneDTO);
            validarAtributos(telefone);
            return telefoneRepository.save(telefone);
        }
        return update(telefoneDTO);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private Telefone update(TelefoneDTO telefoneDTO) {
        Optional<Telefone> dbTelefone = telefoneRepository.findById(telefoneDTO.id());

        if (dbTelefone.isEmpty()) {
            return null;
        }
        Telefone upTelefone = dbTelefone.get();
        upTelefone.setNumero(telefoneDTO.numero());
        upTelefone.setTipo(TipoTelefone.convertTelefoneValue(telefoneDTO.tipo()));
        Pessoa pessoa = pessoaService.findByNome(telefoneDTO.pessoa());
        upTelefone.setPessoa(pessoa);
        upTelefone.setId(telefoneDTO.id());
        Telefone telefone = telefoneRepository.save(upTelefone);
        return telefone;
    }

    @Override
    @Transactional(readOnly = true)
    public TelefoneDTO buscarTelefonePorId(UUID id) {
        return telefoneRepository.findById(id).map(telefone -> this.toDTO(telefone)).get();

    }

    @Override
    @Transactional(readOnly = true)
    public void delete(UUID id) {
        telefoneRepository.deleteById(id);
    }

    @Override
    public TelefoneDTO buscarPorNumero(String numero) {
        return telefoneRepository.findByNumero(numero).map(this::toDTO).get();

    }

    @Override
    public List<TelefoneDTO> list(Pageable pageable) {
        return telefoneRepository.searchAll(pageable).stream().map(this::toDTO).collect(Collectors.toList());

    }

    private void validarAtributos(Telefone t) {
        Optional<Telefone> telefone = telefoneRepository.findByNumero(t.getNumero());
        if (telefone.isPresent() && !Objects.equals(telefone.get().getId(), t.getId())) {
            throw new DataIntegrityViolationException("número já cadastro no sistema!");
        }

    }

    @Override
    public TelefoneDTO criar(UUID pessoa_id, TelefoneDTO telefone) {
        Pessoa pessoa = pessoaService.findById(pessoa_id).get();
        return new TelefoneDTO(telefone.id(), telefone.numero(), telefone.tipo(), pessoa.getNome());
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.TELEFONE);
        Page<Telefone> page = datatables.getSearch().isEmpty() ? telefoneRepository.findAll(datatables.getPageable())
                : telefoneRepository.findAllByTelefone(TipoTelefone.convertTelefoneValue(datatables.getSearch()), datatables.getPageable());
        return datatables.getResponse(page);
    }

    protected TelefoneDTO toDTO(Telefone telefone) {
        String pessoa = (telefone.getPessoa().getId() == null) ? null : telefone.getPessoa().getNome();
        return new TelefoneDTO(telefone.getId(), telefone.getNumero(), telefone.getTipo().getValue(), pessoa);
    }

    protected Telefone toEntity(TelefoneDTO dto) {
        if (dto == null) {
            return null;
        }
        Telefone telefone = new Telefone();
        telefone.setId(dto.id());
        telefone.setNumero(dto.numero());
        telefone.setTipo(TipoTelefone.convertTelefoneValue(dto.tipo()));
        Pessoa pessoa = pessoaService.findByNome(dto.pessoa());
        telefone.setPessoa(pessoa);
        return telefone;
    }

    @Override
    public List<Telefone> getTelefones() {
        return telefoneRepository.findAll(Sort.by("id"));
    }

}
