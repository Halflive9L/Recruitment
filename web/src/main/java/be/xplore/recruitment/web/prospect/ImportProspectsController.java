package be.xplore.recruitment.web.prospect;

import be.xplore.recruitment.domain.prospect.ImportProspects;
import be.xplore.recruitment.domain.prospect.ImportProspectsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/prospect")
public class ImportProspectsController {
    @Autowired
    private ImportProspects importProspects;

    @RequestMapping(method = RequestMethod.POST, value = "/importprospects")
    public ResponseEntity<JsonImportResult> importProspects(@RequestParam("file") MultipartFile uploaded) {
        JsonProspectImportResponseModelPresenter presenter = new JsonProspectImportResponseModelPresenter();
        return tryImportProspects(uploaded, presenter);
    }

    private ResponseEntity<JsonImportResult> tryImportProspects(@RequestParam("file") MultipartFile uploaded,
                                                                JsonProspectImportResponseModelPresenter presenter) {
        try {
            return tryImport(uploaded, presenter);
        } catch (IOException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<JsonImportResult> tryImport(@RequestParam("file") MultipartFile uploaded,
                                                       JsonProspectImportResponseModelPresenter presenter)
            throws IOException {
        ImportProspectsRequest request = new ImportProspectsRequest(uploaded.getInputStream());
        importProspects.importProspects(request, presenter);
        return presenter.getResponseEntity();
    }
}
