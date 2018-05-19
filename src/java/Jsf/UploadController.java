package Jsf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

@Named("uploadController")
@SessionScoped
public class UploadController implements Serializable {

    private UploadedFile file;
    private String archivo;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public UploadController() {
    }

    private String destination = "D:\\Conciliaciones\\";

    public void upload(FileUploadEvent event) {
        String extValidate;
        FacesMessage msg = new FacesMessage("Aviso! ", event.getFile().getFileName() + " fue subido.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        if (event.getFile() != null) {
            this.archivo=destination+event.getFile().getFileName();
            try {
                copyFile(event.getFile().getFileName(), event.getFile().getInputstream());

            } catch (IOException e) {
                Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, e);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Wrong", "Error Uploading     file..."));

                e.printStackTrace();
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Wrong", "Select File!"));
        }
    }

    public void copyFile(String fileName, InputStream in) {
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
            System.out.println("nombre del archivo "+archivo );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
