package ma3s.fintech.war;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Named
@RequestScoped
public class FileDownloadView {
    private StreamedContent file;

    public FileDownloadView() {
        file = DefaultStreamedContent.builder()
                .name("Alemania.csv")
                .contentType("text/csv")
                .stream(() -> {
                    try {
                        return new FileInputStream("/opt/jboss/wildfly/docs/Alemania.csv");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .build();
    }

    public StreamedContent getFile() {
        return file;
    }
}
