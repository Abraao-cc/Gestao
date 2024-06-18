package com.timuila.gestao.controlle;

import com.timuila.gestao.dominio.Departamento;
import com.timuila.gestao.dtos.DepartamentoDTO;
import com.timuila.gestao.services.DepartamentoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrativo
 */
@RestController
@RequestMapping("/departamentos")
public class DepartamentoControlle {

    private final DepartamentoService departamentoService;

    public DepartamentoControlle(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping("/add")
    public ResponseEntity<Departamento> saveDepartamento(@RequestBody DepartamentoDTO cargo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoService.save(cargo));
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> getAllCargos() {

        return ResponseEntity.status(HttpStatus.OK).body(departamentoService.getDepartamentos());
    }
}
