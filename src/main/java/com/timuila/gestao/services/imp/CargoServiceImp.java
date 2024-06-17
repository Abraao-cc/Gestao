
package com.timuila.gestao.services.imp;
import com.timuila.gestao.datatables.Datatables;
import com.timuila.gestao.datatables.DatatablesColunas;
import com.timuila.gestao.dominio.Cargo;
import com.timuila.gestao.repositorys.CargoRepository;
import com.timuila.gestao.services.CargoService;
import com.timuila.gestao.util.CustomCollectors;
import com.timuila.gestao.util.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ti
 */
@Service
public class CargoServiceImp implements CargoService {

    private final CargoRepository cr;
    private final Datatables datatables;

    public CargoServiceImp(CargoRepository cr, Datatables datatables) {
        this.cr = cr;
        this.datatables = datatables;
    }
   
    
      

    @Override
    @Transactional(readOnly = true)
    public Map<UUID, String> cargos() {
        return cr.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Cargo::getId, Cargo::getNome));

    }

    @Override
    @Transactional(readOnly = true)
    public List<Cargo> lista() {
        return cr.findAll(Sort.by("id")).stream().collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Cargo findById(UUID id) {
        return cr.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)+ " Este id não consta no bd! "));
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void salvar(Cargo cargo) {
        cargo.getId();
        validarAtributos(cargo);

        if (cargo.getId() == null) {

            cr.save(cargo);
        }

        update(cargo);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    protected Cargo update(Cargo c) {
        Optional<Cargo> cargo = cr.findById(c.getId());
        if (cargo.isEmpty()) {
            return null;
        }

        var ca = cargo.get();
        ca.setNome(c.getNome());
        ca.setId(c.getId());

        return cr.save(ca);

    }

    @Override
    public void delete(UUID id) {
        cr.delete(cr.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)+ " Este id não consta no bd! ")));
    }

    private void validarAtributos(Cargo c) {
        Optional<Cargo> cargo = cr.findByNome(c.getNome());
        if (cargo.isPresent() && !Objects.equals(cargo.get().getId(), c.getId())) {
            throw new DataIntegrityViolationException("cargo já cadastro no sistema!");
        }

    }

    @Override
    public Cargo findByNome(String nome) {
        return cr.findByNome(nome).get();//orElseThrow(() -> new NotFoundException(nome+ " Este id não consta no bd! "));
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> buscarTodos(HttpServletRequest request) {
        datatables.setRequest(request);
        datatables.setColunas(DatatablesColunas.CARGO);
        Page<Cargo> page = datatables.getSearch().isEmpty() ? cr.findAll(datatables.getPageable())
                : cr.searchAll(datatables.getSearch(), datatables.getPageable());
        return datatables.getResponse(page);
    }
    
    /** public String getReferencedWarning(final Long id) {
        final Cargo cargo = cr.findById(id)
                .orElseThrow(NotFoundException::new);
        final Funcionario cargoFuncionario = funcionarioService.findFirstByCargo(cargo);
        if (cargoFuncionario != null) {
            return WebUtils.getMessage("cargo.funcionario.cargo.referenced", cargoFuncionario.getId());
        }
        return null;
    }**/

}

