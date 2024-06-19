
package com.timuila.gestao.base;

import com.timuila.gestao.emuns.TipoPessoa;
import java.util.List;
import java.util.UUID;
import com.timuila.gestao.services.PessoaService;

/**
 *
 * @author Administrativo
 */
public class Juridica implements PessoaService{

    public Juridica() {
         System.out.println("\nJurídica");
    }

    @Override
    public TipoPessoa save(TipoPessoa tipo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TipoPessoa buscarPessoaPorId(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TipoPessoa> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
