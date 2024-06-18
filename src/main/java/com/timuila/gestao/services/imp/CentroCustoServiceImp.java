package com.timuila.gestao.services.imp;

import com.timuila.gestao.datatables.Datatables;
import com.timuila.gestao.datatables.DatatablesColunas;
import com.timuila.gestao.dominio.CentroCusto;
import com.timuila.gestao.dominio.PessoaJuridica;
import com.timuila.gestao.dtos.CentroCustoDTO;
import com.timuila.gestao.repositorys.CentroCustoRepository;
import com.timuila.gestao.services.CentroCustoService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author ti
 */
@Service
public class CentroCustoServiceImp implements CentroCustoService {

    private final CentroCustoRepository centroCustoRepository;
    private final Datatables datatables;

    public CentroCustoServiceImp(CentroCustoRepository centroCustoRepository, Datatables datatables) {
        this.centroCustoRepository = centroCustoRepository;

        this.datatables = datatables;
    }

    @Override
    public List<CentroCusto> getCentroCustos() {

        return centroCustoRepository.findAll(Sort.by("id"));
    }

    @Override
    public CentroCusto save(CentroCustoDTO centroCustoDTO) {
        CentroCusto centroCusto = this.toEntity(centroCustoDTO);

        validarAtributos(centroCusto);
        if (centroCustoDTO.id() == null) {
            return centroCustoRepository.save(centroCusto);
        }

        return update(centroCustoDTO);
    }

    private CentroCusto update(CentroCustoDTO centroCustoDTO) {
        CentroCusto centroCusto = centroCustoRepository.findById(centroCustoDTO.id()).get();
        centroCusto.setNome(centroCustoDTO.nome());
        centroCusto.setId(centroCustoDTO.id());
        return centroCustoRepository.save(centroCusto);
    }

    @Override
    public CentroCustoDTO get(UUID id) {
        return centroCustoRepository.findById(id).map(this::toDTO).get();
    }

    private void validarAtributos(CentroCusto c) {
        Optional<CentroCusto> centroCusto = centroCustoRepository.findByNome(c.getNome());
        if (centroCusto.isPresent() && !Objects.equals(centroCusto.get().getId(), c.getId())) {
            throw new DataIntegrityViolationException("nome já cadastro no sistema!");
        }

    }

    @Override
    public void delete(UUID id) {
        centroCustoRepository.deleteById(id);
    }

    @Override
    public boolean nomeExists(String nome) {
        return centroCustoRepository.existsByNomeIgnoreCase(nome);
    }

    public String getReferencedWarning(Long id) {
        /**
         * CentroCusto centroCusto = centroCustoRepository.findById(id).get();
         * Funcionario centroCustoFuncionario =
         * funcionarioRepository.findFirstByCentro(centroCusto); if
         * (centroCustoFuncionario != null) { return
         * WebUtils.getMessage("centroCusto.funcionario.centroCusto.referenced",
         * centroCustoFuncionario.getId()); }
         */
        return null;
    }

    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CENTROCUSTO);
        Page<CentroCusto> page = datatables.getSearch().isEmpty() ? centroCustoRepository.findAll(datatables.getPageable())
                : centroCustoRepository.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }

    @Override
    public CentroCusto findByNome(String nome) {
        return centroCustoRepository.findByNome(nome).get();

    }

    protected CentroCustoDTO toDTO(CentroCusto centroCusto) {
        UUID empresa = (centroCusto.getEmpresa().getId() == null) ? null : centroCusto.getEmpresa().getId();
        return new CentroCustoDTO(centroCusto.getId(), centroCusto.getNome(), empresa);

    }

    protected CentroCusto toEntity(CentroCustoDTO dto) {
        CentroCusto centro = new CentroCusto();
        centro.setNome(dto.nome());
        centro.setEmpresa(new PessoaJuridica(dto.empresa()));
        return centro;
    }

}
