package com.timuila.gestao.services;

import com.timuila.gestao.dominio.Autor;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface AutorService {

    Autor save(Autor autor);

    Autor update(Autor autor);

    void delete(UUID id);

    List<Autor> getAutors();
}
