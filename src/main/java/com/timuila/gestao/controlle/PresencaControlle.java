
package com.timuila.gestao.controlle;

import com.timuila.gestao.domain.Presenca;
import com.timuila.gestao.dtos.FuncionarioResponseDTO;
import com.timuila.gestao.dtos.PresencaDTO;
import com.timuila.gestao.services.PresencaService;
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
@RequestMapping("/presencas")
public class PresencaControlle {
      private final PresencaService presencaService;

    public PresencaControlle(PresencaService presencaService) {
        this.presencaService = presencaService;
    }

   

    @PostMapping("/add")
    public ResponseEntity<Presenca> savePresenca(@RequestBody PresencaDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(presencaService.save(dto));
    }
    @GetMapping
    public ResponseEntity<List<PresencaDTO>> getAllPresencas() {

        return ResponseEntity.status(HttpStatus.OK).body(presencaService.getPresencas());
    }
}
