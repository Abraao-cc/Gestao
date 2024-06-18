package com.timuila.gestao.controlle;

import com.timuila.gestao.dominio.Cargo;
import com.timuila.gestao.dtos.CargoDTO;
import com.timuila.gestao.services.CargoService;
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
@RequestMapping("/cargos")
public class CargoControlle {

    private final CargoService cargoService;

    public CargoControlle(CargoService cs) {
        this.cargoService = cs;
    }

  

    @PostMapping("/add")
    public ResponseEntity<Cargo> saveFuncionario(@RequestBody CargoDTO cargo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cargoService.save(cargo));
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> getAllCargos() {

        return ResponseEntity.status(HttpStatus.OK).body(cargoService.lista());
    }

}
