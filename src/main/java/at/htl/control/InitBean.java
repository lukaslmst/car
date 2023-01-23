package at.htl.control;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class InitBean {

    @ConfigProperty(name ="carfile")
    String carFileName;

    @ConfigProperty(name = "personfile")
    String personFileName;

    void startUp(@Observes StartupEvent event) {
        importCarsIntoOb(carFileName);
        importFile(personFileName);
        importFile(carFileName);
    }

    private void importCarsIntoOb(String carFileName) {

    }

    List<String> importFile(String filename) {

        try {
            var path = Paths.get(getClass().getClassLoader()
                  .getResource(filename).toURI());

            Stream<String> lines = Files.lines(path);
            return lines.toList();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

     }



}
