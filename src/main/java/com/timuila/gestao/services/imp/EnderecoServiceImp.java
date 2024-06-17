package com.timuila.gestao.services.imp;

import com.timuila.gestao.datatables.Datatables;
import com.timuila.gestao.datatables.DatatablesColunas;
import com.timuila.gestao.dominio.Endereco;
import com.timuila.gestao.dominio.Pessoa;
import com.timuila.gestao.dtos.EnderecoRecord;
import com.timuila.gestao.repositorys.EnderecoRepository;
import com.timuila.gestao.repositorys.PessoaRepository;
import com.timuila.gestao.services.EnderecoService;
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
public class EnderecoServiceImp implements EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaService;
    private final Datatables datatables;

    public EnderecoServiceImp(EnderecoRepository enderecoRepository, PessoaRepository pessoaService, Datatables datatables) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaService = pessoaService;
        this.datatables = datatables;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnderecoRecord> list(Pageable pageable) {
        return enderecoRepository.searchAll(pageable).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public EnderecoRecord salvar(EnderecoRecord enderecoDTO) {
        if (enderecoDTO.id() == null) {
            Endereco endereco = this.toEntity(enderecoDTO);
            validarAtributos(endereco);
            Pessoa pessoa = pessoaService.findByNome(enderecoDTO.pessoa()).get();
            endereco.setPessoa(pessoa);
            return this.toDTO(enderecoRepository.save(endereco));

        }
        return update(enderecoDTO);

    }

    @Transactional(readOnly = false)
    public EnderecoRecord update(EnderecoRecord dto) {

        Endereco endereco = enderecoRepository.findById(dto.id()).get();

        endereco.setUf(dto.uf());
        endereco.setCidade(dto.cidade());
        endereco.setBairro(dto.bairro());
        endereco.setRua(dto.rua());
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        Pessoa pessoa = pessoaService.findByNome(dto.pessoa()).get();
        endereco.setPessoa(pessoa);

        return this.toDTO(enderecoRepository.save(endereco));
    }

    @Transactional(readOnly = true)
    @Override
    public EnderecoRecord buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep).map(this::toDTO).get();
    }

    @Transactional(readOnly = true)
    @Override
    public EnderecoRecord criar(UUID pessoa_id) {

        Optional<Endereco> endereco = enderecoRepository.findByPessoaId(pessoa_id);
        if (endereco.isEmpty() || endereco.get().getId() == null) {
            Pessoa pessoa = pessoaService.findById(pessoa_id).get();
            Endereco address = new Endereco(pessoa);
            return this.toDTO(address);
        }
        return this.toDTO(endereco.get());
    }

    private void validarAtributos(Endereco request) {
        Optional<Endereco> endereco = enderecoRepository.findByCep(request.getCep());
        if (endereco.isPresent() && !Objects.equals(endereco.get().getId(), request.getId()) && !Objects.equals(endereco.get().getPessoa().getId(), request.getPessoa().getId())) {
            throw new DataIntegrityViolationException("cep já cadastro no sistema!");
        }
        endereco = enderecoRepository.findByPessoaId(request.getPessoa().getId());
        if (endereco.isPresent() && !Objects.equals(endereco.get().getPessoa().getId(), request.getPessoa().getId())) {
            throw new DataIntegrityViolationException("pessoa já cadastro no sistema!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EnderecoRecord buscarEnderecoPorId(UUID id) {
        return enderecoRepository.findById(id).map(this::toDTO).get();
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(UUID id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.ENDERECO);
        Page<Endereco> page = datatables.getSearch().isEmpty() ? enderecoRepository.findAll(datatables.getPageable())
                : enderecoRepository.findAllByEndereco(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public boolean pessoaExists(UUID id) {
        return pessoaService.existsById(id);
    }

    protected EnderecoRecord toDTO(Endereco e) {

        String pessoa = (e.getPessoa().getId() == null) ? null : e.getPessoa().getNome();

        return new EnderecoRecord(e.getId(), e.getUf(), e.getCidade(), e.getBairro(), e.getRua(), e.getCep(), e.getNumero(), e.getComplemento(), pessoa);
    }

    protected Endereco toEntity(EnderecoRecord dto) {
        Endereco endereco = new Endereco();
        endereco.setUf(dto.uf());
        endereco.setCidade(dto.cidade());
        endereco.setBairro(dto.bairro());
        endereco.setRua(dto.rua());
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setPessoa(new Pessoa(dto.pessoa()));
        return endereco;
    }

}
