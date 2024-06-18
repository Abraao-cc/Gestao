package com.timuila.gestao.controlle;

import com.timuila.gestao.dominio.CentroCusto;
import com.timuila.gestao.dtos.CentroCustoDTO;
import com.timuila.gestao.services.CentroCustoService;
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
@RequestMapping("/centroCustos")
public class CentroCustoControlle {

    private final CentroCustoService centroCustoService;

    public CentroCustoControlle(CentroCustoService centroCustoService) {
        this.centroCustoService = centroCustoService;
    }

    @PostMapping("/add")
    public ResponseEntity<CentroCusto> saveCentroCusto(@RequestBody CentroCustoDTO centroCustoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(centroCustoService.save(centroCustoDTO));
    }

    @GetMapping
    public ResponseEntity<List<CentroCusto>> getAllCentroCustos() {

        return ResponseEntity.status(HttpStatus.OK).body(centroCustoService.getCentroCustos());
    }

}
