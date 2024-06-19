
package com.timuila.gestao.controlle;

import com.timuila.gestao.domain.Endereco;
import com.timuila.gestao.dtos.EnderecoDTO;
import com.timuila.gestao.services.EnderecoService;
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
@RequestMapping("/enderecos")
public class EnderecoControlle {
      private final EnderecoService enderecoService;

    public EnderecoControlle(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

   

   
    @PostMapping("/add")
    public ResponseEntity<Endereco> saveEndereco(@RequestBody EnderecoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos() {

        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getEnderecos());
    }
}
