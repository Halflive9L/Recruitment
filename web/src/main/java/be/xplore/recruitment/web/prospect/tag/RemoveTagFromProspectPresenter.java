package be.xplore.recruitment.web.prospect.tag;

import be.xplore.recruitment.domain.tag.RemoveTagResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Consumer;

/**
 * @author Stijn Schack
 * @since 8/11/2017
 */
public class RemoveTagFromProspectPresenter implements Consumer<RemoveTagResponseModel>{
    private ResponseEntity<String> responseEntity;
    @Override
    public void accept(RemoveTagResponseModel removeTagResponseModel) {
        if (responseEntity==null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            responseEntity = new ResponseEntity<>(removeTagResponseModel.getTag(), HttpStatus.OK);
        }
    }

    public ResponseEntity<String> getResponseEntity() {
        return responseEntity;
    }
}
