package com.timuila.gestao.controlle;

import com.timuila.gestao.dominio.Funcionario;
import com.timuila.gestao.dtos.FuncionarioDTO;
import com.timuila.gestao.dtos.FuncionarioResponseDTO;
import com.timuila.gestao.services.FuncionarioService;
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
@RequestMapping("/funcionarios")
public class FuncionarioControlle {

    private final FuncionarioService funcionarioService;

    public FuncionarioControlle(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

  

    @PostMapping("/add")
    public ResponseEntity<Funcionario> saveFuncionario(@RequestBody FuncionarioDTO funcionario) {

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionario));
    }
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> getAllFuncionarios() {

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.list());
    }

}
