package Jpa;

import Modelo.Compra;
import Modelo.Detallecompra;
import Modelo.Detalledespachopicadora;
import Modelo.Detalleproduccionpicadora;
import Modelo.Distribucionusomaquinaria;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DistribucionusomaquinariaFacadeLocal {

    void create(Distribucionusomaquinaria distribucionusomaquinaria);

    void edit(Distribucionusomaquinaria distribucionusomaquinaria);

    void remove(Distribucionusomaquinaria distribucionusomaquinaria);

    Distribucionusomaquinaria find(Object id);

    List<Distribucionusomaquinaria> findAll();

    List<Distribucionusomaquinaria> findRange(int[] range);

    int count();
}
