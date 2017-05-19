package Jpa;

import Modelo.Compra;
import Modelo.Detallecompra;
import Modelo.Detalledespachopicadora;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DetalledespachopicadoraFacadeLocal {

    void create(Detalledespachopicadora detalledespachopicadora);

    void edit(Detalledespachopicadora detalledespachopicadora);

    void remove(Detalledespachopicadora detalledespachopicadora);

    Detalledespachopicadora find(Object id);

    List<Detalledespachopicadora> findAll();

    List<Detalledespachopicadora> findRange(int[] range);

    int count();
}
