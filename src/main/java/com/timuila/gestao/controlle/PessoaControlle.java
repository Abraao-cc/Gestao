package com.timuila.gestao.controlle;

import com.timuila.gestao.dominio.Pessoa;
import com.timuila.gestao.dtos.PessoaRecord;
import com.timuila.gestao.services.PessoaService;
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
@RequestMapping("/pessoa")
public class PessoaControlle {

    private final PessoaService pessoaService;

    public PessoaControlle(PessoaService bookService) {
        this.pessoaService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Pessoa> saveBook(@RequestBody PessoaRecord pessoaRecord) {

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.save(pessoaRecord));
    }
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllBooks() {

        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoas());
    }

}
