package Jpa;

import Modelo.Compra;
import Modelo.Detallecompra;
import Modelo.Detalledespachopicadora;
import Modelo.Detalleproduccionpicadora;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DetalleproduccionpicadoraFacadeLocal {

    void create(Detalleproduccionpicadora detalleproduccionpicadora);

    void edit(Detalleproduccionpicadora detalleproduccionpicadora);

    void remove(Detalleproduccionpicadora detalleproduccionpicadora);

    Detalleproduccionpicadora find(Object id);

    List<Detalleproduccionpicadora> findAll();

    List<Detalleproduccionpicadora> findRange(int[] range);

    int count();
    
    List<Detalleproduccionpicadora> buscarmovimientoporfecha (Date fechaini, Date fechafinish);
}
