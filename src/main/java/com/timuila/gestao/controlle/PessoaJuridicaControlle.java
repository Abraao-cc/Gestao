package com.timuila.gestao.controlle;

import com.timuila.gestao.domain.PessoaJuridica;
import com.timuila.gestao.dtos.PessoaJuridicaDTO;
import com.timuila.gestao.services.PessoaJuridicaService;
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
@RequestMapping("/pessoaJuridica")
public class PessoaJuridicaControlle {

    private final PessoaJuridicaService pessoaService;

    public PessoaJuridicaControlle(PessoaJuridicaService bookService) {
        this.pessoaService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<PessoaJuridica> savePessoaJuridica(@RequestBody PessoaJuridicaDTO pessoaJuridicaDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaJuridicaDTO));
    }
    @GetMapping
    public ResponseEntity<List<PessoaJuridica>> getAllPessoaJuridicas() {

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.list());
    }

}
