package com.timuila.gestao.services;

import com.timuila.gestao.dominio.Publisher;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Administrativo
 */
public interface PublisherService {

    Publisher save(Publisher publisher);

    Publisher update(Publisher publisher);

    void delete(UUID id);

    List<Publisher> getPublishers();
}
