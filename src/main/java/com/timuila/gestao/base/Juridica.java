
package com.timuila.gestao.base;

import com.timuila.gestao.emuns.TipoPessoa;

/**
 *
 * @author Administrativo
 */
public class Juridica implements Ppessoa{

    public Juridica() {
         System.out.println("\nJurídica");
    }

    @Override
    public TipoPessoa tipo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String save() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Ppessoa clone() {
          return new Juridica();
    }

  
    
}
