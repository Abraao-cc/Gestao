package com.timuila.gestao.services.imp;

import com.timuila.gestao.dominio.PessoaJuridica;
import com.timuila.gestao.dtos.PessoaJuridicaDTO;
import com.timuila.gestao.dtos.PessoaJuridicaResponseDTO;
import com.timuila.gestao.repositorys.PessoaJuridicaRepository;
import com.timuila.gestao.services.PessoaJuridicaService;
import com.timuila.gestao.util.NotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrativo
 */
@Service
public class PessoaJuridicaImp implements PessoaJuridicaService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public PessoaJuridicaImp(PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public PessoaJuridica save(PessoaJuridicaDTO pessoaJuridicaDTO) {
        PessoaJuridica pessoaJuridica = this.toEntity(pessoaJuridicaDTO);
        validarAtributos(pessoaJuridica);
        if (pessoaJuridicaDTO.id() == null) {
            return pessoaJuridicaRepository.save(pessoaJuridica);
        }

        return update(pessoaJuridicaDTO);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    protected PessoaJuridica update(PessoaJuridicaDTO dto) {
        PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findByCnpj(dto.cnpj()).get();
        pessoaJuridica.setId(dto.id());
        pessoaJuridica.setNome(dto.nome());
        pessoaJuridica.setNome(dto.nome());
        pessoaJuridica.setSobrenome(dto.sobrenome());
        pessoaJuridica.setNascimento(dto.abertura());
        pessoaJuridica.setCnpj(dto.cnpj());
        pessoaJuridica.setIe(dto.ie());
        pessoaJuridica.setIm(dto.im());

        return pessoaJuridicaRepository.save(pessoaJuridica);

    }

    private void validarAtributos(PessoaJuridica pj) {
        Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaRepository.findByCnpj(pj.getCnpj());
        if (pessoaJuridica.isPresent() && !Objects.equals(pessoaJuridica.get().getId(), pj.getId())) {
            throw new DataIntegrityViolationException("clt já cadastro no sistema!");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public PessoaJuridica buscarPessoaJuridicaPorId(UUID id) {
        return pessoaJuridicaRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PessoaJuridica> list() {
        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaRepository.findAll(Sort.by("id"));
        return pessoasJuridicas;
    }

    @Override
    public void delete(UUID id) {
        //pessoaJuridicaRepository.delete(pessoaJuridicaRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " este id não consta no bd! ")));
        pessoaJuridicaRepository.delete(pessoaJuridicaRepository.findById(id).orElseThrow(() -> new NotFoundException(id + " este id não consta no bd! ")));
    }

    protected PessoaJuridicaDTO toDTO(PessoaJuridica pessoaJuridica) {

        return new PessoaJuridicaDTO(pessoaJuridica.getId(),pessoaJuridica.getNome(), pessoaJuridica.getSobrenome(), pessoaJuridica.getNascimento(), pessoaJuridica.getCnpj(), pessoaJuridica.getIe(), pessoaJuridica.getIm());
    }

    protected PessoaJuridica toEntity(PessoaJuridicaDTO pessoaJuridicaDTO) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
       pessoaJuridica.setId(pessoaJuridicaDTO.id());
        pessoaJuridica.setNome(pessoaJuridicaDTO.nome());
        pessoaJuridica.setSobrenome(pessoaJuridicaDTO.sobrenome());
        pessoaJuridica.setNascimento(pessoaJuridicaDTO.abertura());
        pessoaJuridica.setCnpj(pessoaJuridicaDTO.cnpj());
        pessoaJuridica.setIe(pessoaJuridicaDTO.ie());
        pessoaJuridica.setIm(pessoaJuridicaDTO.im());
        return pessoaJuridica;
    }

}
