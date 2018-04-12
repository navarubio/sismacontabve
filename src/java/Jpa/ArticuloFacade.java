package Jpa;

import Modelo.Articulo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader; 

@Stateless
public class ArticuloFacade extends AbstractFacade<Articulo> implements ArticuloFacadeLocal {

    @PersistenceContext(unitName = "SismacontabecPU")
    private EntityManager em;
    Articulo art;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticuloFacade() {
        super(Articulo.class);
    }

    @Override
    public Articulo buscarArticulo(String codigo) {
        Articulo art = null;
        String consulta;
        try {
            consulta = "From Articulo a where a.codigo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, codigo);

            List<Articulo> lista = query.getResultList();
            if (!lista.isEmpty()) {
                art = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return art;
    }
    
    @Override
    public Articulo buscarXcodigo(String codigo) {
        Articulo art = null;
        String consulta;
        try {
//            consulta = "From Articulo a where a.codigo = ?1";
            Query query = em.createNamedQuery("Articulo.findByCodigo");
            query.setParameter("codigo", codigo);

            List<Articulo> lista = query.getResultList();
            if (!lista.isEmpty()) {
                art = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return art;
    }
    
    public class reporteCliente {

        public void getReporte(String ruta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
            Connection conexion;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sismacontab_ec", "root", "091095");

            //Se definen los parametros si es que el reporte necesita
            Map parameter = new HashMap();

            try {
                File file = new File(ruta);

                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.addHeader("Content-Type", "application/pdf");

                JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conexion);

                JRExporter jrExporter = null;
                jrExporter = new JRPdfExporter();
                jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());

                if (jrExporter != null) {
                    try {
                        jrExporter.exportReport();
                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conexion != null) {
                    try {
                        conexion.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @Override
    public List<Articulo> listadoArticulos() {
        String consulta;
        List<Articulo> lista = null;
        try {
            consulta = "From Articulo a order by a.descripcion";
            Query query = em.createQuery(consulta);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    @Override
    public List<Articulo> listadoAgregadospicadora() {
        String consulta;
        int subgrupo=15;
        List<Articulo> lista = null;
        try {
            consulta = "From Articulo a where a.idsubgrupo.idsubgrupo = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, subgrupo);
            lista = query.getResultList();
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
}
