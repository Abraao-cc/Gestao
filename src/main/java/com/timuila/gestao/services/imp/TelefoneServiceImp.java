package com.timuila.gestao.services.imp;

import com.timuila.gestao.datatables.Datatables;
import com.timuila.gestao.datatables.DatatablesColunas;
import com.timuila.gestao.dominio.Pessoa;
import com.timuila.gestao.dominio.Telefone;
import com.timuila.gestao.dtos.TelefoneRecord;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrativo
 */
@Service
public class TelefoneServiceImp implements TelefoneService {

    private final TelefoneRepository tr;
    private final Datatables datatables;
    private final PessoaRepository pessoaService;

    public TelefoneServiceImp(TelefoneRepository tr, Datatables datatables, PessoaRepository pessoaService) {
        this.tr = tr;
        this.datatables = datatables;
        this.pessoaService = pessoaService;
    }

    @Override
    @Transactional(readOnly = false)
    public UUID salvar(TelefoneRecord telefoneDTO) {

        Telefone telefone = this.toEntity(telefoneDTO);
        validarAtributos(telefone);
        if (telefoneDTO.id() == null) {
            Pessoa pessoa = pessoaService.findByNome(telefoneDTO.pessoa()).get();
            telefone.setPessoa(pessoa);
            return tr.save(telefone).getPessoa().getId();
        }
        return update(telefoneDTO);
    }

    @Transactional(readOnly = false)
    private UUID update(TelefoneRecord telefoneDTO) {
        Optional<Telefone> dbTelefone = tr.findById(telefoneDTO.id());

        if (dbTelefone.isEmpty()) {
            return null;
        }
        Telefone upTelefone = dbTelefone.get();
        upTelefone.setNumero(telefoneDTO.numero());
        upTelefone.setTipo(TipoTelefone.convertTelefoneValue(telefoneDTO.tipo()));
        Pessoa pessoa = pessoaService.findByNome(telefoneDTO.pessoa()).get();
        upTelefone.setPessoa(pessoa);
        upTelefone.setId(telefoneDTO.id());
        Telefone telefone = tr.save(upTelefone);
        return telefone.getPessoa().getId();
    }

    @Override
    @Transactional(readOnly = true)
    public TelefoneRecord buscarTelefonePorId(UUID id) {
        return tr.findById(id).map(telefone -> this.toDTO(telefone)).get();

    }

    @Override
    @Transactional(readOnly = true)
    public void delete(UUID id) {
        tr.deleteById(id);
    }

    @Override
    public TelefoneRecord buscarPorNumero(String numero) {
        return tr.findByNumero(numero).map(this::toDTO).get();

    }

    @Override
    public List<TelefoneRecord> list(Pageable pageable) {
        return tr.searchAll(pageable).stream().map(this::toDTO).collect(Collectors.toList());

    }

    private void validarAtributos(Telefone t) {
        Optional<Telefone> telefone = tr.findByNumero(t.getNumero());
        if (telefone.isPresent() && !Objects.equals(telefone.get().getId(), t.getId())) {
            throw new DataIntegrityViolationException("número já cadastro no sistema!");
        }

    }

    @Override
    public TelefoneRecord criar(UUID pessoa_id, TelefoneRecord telefone) {
        Pessoa pessoa = pessoaService.findById(pessoa_id).get();
        return new TelefoneRecord(telefone.id(), telefone.numero(), telefone.tipo(), pessoa.getNome());
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.TELEFONE);
        Page<Telefone> page = datatables.getSearch().isEmpty() ? tr.findAll(datatables.getPageable())
                : tr.findAllByTelefone(TipoTelefone.convertTelefoneValue(datatables.getSearch()), datatables.getPageable());
        return datatables.getResponse(page);
    }

    protected TelefoneRecord toDTO(Telefone telefone) {
        String pessoa = (telefone.getPessoa().getNome() == null) ? null : telefone.getPessoa().getNome();
        return new TelefoneRecord(telefone.getId(), telefone.getNumero(), telefone.getTipo().getValue(), pessoa);
    }

    protected Telefone toEntity(TelefoneRecord dto) {
        if (dto == null) {
            return null;
        }
        Telefone telefone = new Telefone();
        telefone.setId(dto.id());
        telefone.setNumero(dto.numero());
        telefone.setTipo(TipoTelefone.convertTelefoneValue(dto.tipo()));
        telefone.setPessoa(new Pessoa(dto.pessoa()));
        return telefone;
    }

}
