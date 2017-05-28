package Jpa;

import Modelo.Compra;
import Modelo.Detallecompra;
import Modelo.Detalledespachopicadora;
import Modelo.Detallenotacarga;
import Modelo.Detalleproduccionpicadora;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DetallenotacargaFacadeLocal {

    void create(Detallenotacarga detallenotacarga);

    void edit(Detallenotacarga detallenotacarga);

    void remove(Detallenotacarga detallenotacarga);

    Detallenotacarga find(Object id);

    List<Detallenotacarga> findAll();

    List<Detallenotacarga> findRange(int[] range);

    int count();
}
