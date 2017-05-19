package Jpa;

import Modelo.Compra;
import Modelo.Despachopicadora;
import Modelo.Detallecompra;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DespachopicadoraFacadeLocal {

    void create(Despachopicadora despachopicadora);

    void edit(Despachopicadora despachopicadora);

    void remove(Despachopicadora despachopicadora);

    Despachopicadora find(Object id);

    List<Despachopicadora> findAll();

    List<Despachopicadora> findRange(int[] range);

    int count();
}
