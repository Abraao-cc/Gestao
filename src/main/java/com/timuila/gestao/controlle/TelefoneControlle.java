package com.timuila.gestao.controlle;

import com.timuila.gestao.domain.Telefone;
import com.timuila.gestao.dtos.TelefoneDTO;
import com.timuila.gestao.services.TelefoneService;
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
@RequestMapping("/telefones")
public class TelefoneControlle {

    private final TelefoneService telefoneService;

    public TelefoneControlle(TelefoneService telefoneService) {
        this.telefoneService = telefoneService;
    }

    @PostMapping("/add")
    public ResponseEntity<Telefone> saveTelefone(@RequestBody TelefoneDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneService.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<Telefone>> getAllTelefones() {

        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.getTelefones());
    }
}
