package com.timuila.gestao.services.imp;

import com.timuila.gestao.datatables.Datatables;
import com.timuila.gestao.datatables.DatatablesColunas;
import com.timuila.gestao.domain.Endereco;
import com.timuila.gestao.domain.Pessoa;
import com.timuila.gestao.dtos.EnderecoDTO;
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
import org.springframework.data.domain.Sort;
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
    public List<EnderecoDTO> list(Pageable pageable) {
        return enderecoRepository.searchAll(pageable).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Endereco save(EnderecoDTO enderecoDTO) {
        if (enderecoDTO.id() == null) {
            Endereco endereco = this.toEntity(enderecoDTO);
            validarAtributos(endereco);
            return enderecoRepository.save(endereco);

        }
        return update(enderecoDTO);

    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Endereco update(EnderecoDTO dto) {

        Endereco endereco = enderecoRepository.findById(dto.id()).get();

        endereco.setUf(dto.uf());
        endereco.setCidade(dto.cidade());
        endereco.setBairro(dto.bairro());
        endereco.setRua(dto.rua());
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        Pessoa pessoa = pessoaService.findByNome(dto.pessoa());
        endereco.setPessoa(pessoa);

        return enderecoRepository.save(endereco);
    }

    @Transactional(readOnly = true)
    @Override
    public EnderecoDTO buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep).map(this::toDTO).get();
    }

    @Transactional(readOnly = true)
    @Override
    public EnderecoDTO criar(UUID pessoa_id) {

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
    public EnderecoDTO buscarEnderecoPorId(UUID id) {
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

    protected EnderecoDTO toDTO(Endereco endereco) {

        String pessoa = (endereco.getPessoa().getId() == null) ? null : endereco.getPessoa().getNome();

        return new EnderecoDTO(endereco.getId(), endereco.getUf(), endereco.getCidade(), endereco.getBairro(), endereco.getRua(), endereco.getCep(), endereco.getNumero(), endereco.getComplemento(), pessoa);
    }

    protected Endereco toEntity(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setId(dto.id());
        endereco.setUf(dto.uf());
        endereco.setCidade(dto.cidade());
        endereco.setBairro(dto.bairro());
        endereco.setRua(dto.rua());
        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        Pessoa pessoa = pessoaService.findByNome(dto.pessoa());
        endereco.setPessoa(pessoa);
        return endereco;
    }

    @Override
    public List<Endereco> getEnderecos() {
        return enderecoRepository.findAll(Sort.by("id"));
    }

}
