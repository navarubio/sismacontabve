/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jsf;

import Modelo.Conciliacion;
import Modelo.Detalleconsumocajachica;
import Modelo.Movimientobancario;
import java.io.File;
import jxl.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped

public class LectorexcelController implements Serializable {

    private Movimientobancario movimientobanca;
    private Conciliacion concilia;
    private ConciliacionController conciliacionController;
    private List<Movimientobancario> movimientosCargados = null;
    private Date fecha = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private List<Movimientobancario> movimientosNuevos = new ArrayList();

    public LectorexcelController() {
    }

    public Movimientobancario getMovimientobanca() {
        return movimientobanca;
    }

    public void setMovimientobanca(Movimientobancario movimientobanca) {
        this.movimientobanca = movimientobanca;
    }

    public Conciliacion getConcilia() {
        return concilia;
    }

    public void setConcilia(Conciliacion concilia) {
        this.concilia = concilia;
    }

    public List<Movimientobancario> getMovimientosCargados() {
        return movimientosCargados;
    }

    public void setMovimientosCargados(List<Movimientobancario> movimientosCargados) {
        this.movimientosCargados = movimientosCargados;
    }

    public List<Movimientobancario> getMovimientosNuevos() {
        return movimientosNuevos;
    }

    public void setMovimientosNuevos(List<Movimientobancario> movimientosNuevos) {
        this.movimientosNuevos = movimientosNuevos;
    }

//archivoExcel.getNumberOfSheets()
    public void leerArchivoExcel(String archivoDestino) {

        try {
            int sheetNo = 0;
            if (!movimientosNuevos.isEmpty()){
                movimientosNuevos.clear();
            }
                
            Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
//            System.out.println("Número de Hojas\t"+ archivoExcel.getNumberOfSheets());
            if (sheetNo == 0) // Recorre cada hoja                                                                                                                                                       
            {
                Sheet hoja = archivoExcel.getSheet(sheetNo);
                int numColumnas = hoja.getColumns();
                int numFilas = hoja.getRows();
                String data = "";
//                System.out.println("Nombre de la Hoja\t" + archivoExcel.getSheet(sheetNo).getName());
                for (int fila = 1; fila < numFilas ; fila++) {
                    Movimientobancario movi = new Movimientobancario();
                    // Recorre cada fila de la hoja 
                    for (int columna = 0; columna < numColumnas; columna++) {
                        // Recorre cada columna de la fila 
                        data = ((hoja.getCell(columna, fila).getContents()));
                        if (columna == 0) {
                            if (!data.isEmpty()) {
                                fecha = formato.parse(data);
                                movi.setFecha(fecha);
                            }
                        } else if (columna == 1) {
                            if (!data.isEmpty()) {
                                double valuedeb = Double.parseDouble(data.replace(",", "."));
                                movi.setDebito(valuedeb);
                            } else {
                                movi.setDebito(0.0);
                            }
                        } else if (columna == 2) {
                            if (!data.isEmpty()) {
                                double valuecred = Double.parseDouble(data.replace(",", "."));
                                movi.setCredito(valuecred);
                            } else {
                                movi.setCredito(0.0);
                            }
                        } else if (columna == 3) {
                            if (!data.isEmpty()) {
                                double valuetot = Double.parseDouble(data.replace(",", "."));
                                movi.setSaldoactual(valuetot);
                            }
                        }
//                        this.movimientosNuevos.add(movi);
//                        System.out.print(data + " ");
                        data = "";
                    }
                    movi.setIdmovimiento(fila);
                    this.movimientosNuevos.add(movi);
                    //System.out.println("\n");
                }
                sheetNo++;
//                this.movimientosCargados=movimientosNuevos;
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

    }

    public List<Movimientobancario> correrClase() {
        LectorexcelController excel = new LectorexcelController();
        excel.leerArchivoExcel("c://excelconciliacion1.xls");
        return movimientosNuevos;
    }
}

/*    public ArrayList<String[]> readExcelFileToArray(File excelFile){    
 ArrayList<String[]> arrayDatos = new ArrayList<>();
 InputStream excelStream = null;
 try {
 excelStream = new FileInputStream(excelFile);
 // Representación del más alto nivel de la hoja excel.
 HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
 // Elegimos la hoja que se pasa por parámetro.
 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);    
 // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
 HSSFRow hssfRow = hssfSheet.getRow(hssfSheet.getTopRow());
 String [] datos = new String[hssfRow.getLastCellNum()];            
 // Para este ejemplo vamos a recorrer las filas obteniendo los datos que queremos            
 /*        for (Row row: hssfSheet) {                    
 for (Cell cell : row) {
 /* 
 We have those cell types (tenemos estos tipos de celda): 
 CELL_TYPE_BLANK, CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_BOOLEAN, CELL_TYPE_ERROR
 *//*
 datos[cell.getColumnIndex()] =  
 (cell.getCellType() == Cell.CELL_TYPE_STRING)?cell.getStringCellValue():
 (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)?"" + cell.getNumericCellValue():
 (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)?"" + cell.getBooleanCellValue():
 (cell.getCellType() == Cell.CELL_TYPE_BLANK)?"BLANK":
 (cell.getCellType() == Cell.CELL_TYPE_FORMULA)?"FORMULA":
 (cell.getCellType() == Cell.CELL_TYPE_ERROR)?"ERROR":"";                                                                   
 }
 arrayDatos.add(datos); 
 datos = new String[hssfRow.getLastCellNum()];  
 }    */   /*     
 } catch (FileNotFoundException fileNotFoundException) {
 System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
 } catch (IOException ex) {
 System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
 } finally {
 try {
 excelStream.close();
 } catch (IOException ex) {
 System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
 }
 }
 return arrayDatos;
 }
 
 /**     
 * Main method for the tests for the methods of the class <strong>Java
 * read excel</strong> and <strong>Java create excel</strong> 
 * with <a href="https://poi.apache.org/">Apache POI</a>. 
 * <br />
 * Método main para las pruebas para los método de la clase,
 * pruebas de <strong>Java leer excel</strong> y  <strong>Java crear excel</strong>
 * con <a href="https://poi.apache.org/">Apache POI</a>.     
 * @param args 
     
 public static void main(String[] args){
 LectorexcelController javaPoiUtils = new LectorexcelController();  
 ArrayList<String[]> arrayDatosExcel = javaPoiUtils.readExcelFileToArray(new File("/home/xules/codigoxules/apachepoi/PaisesIdiomasMonedas.xls")); 
 int r = 0;
 for (String[] next : arrayDatosExcel) {
 System.out.print("Array Row: " + r++ + " -> ");
 for (int c = 0; c < next.length; c++) {
 System.out.print("[Column " + c + ": " + next + "] ");
 }
 System.out.println();
 }
 }   */
