package pl.mg.rest.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @PostMapping(path = "/fileupload/{name}")
    public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile[] files) {

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        List<String> tempFileNames = new ArrayList<>();
        String tempFileName;
        FileOutputStream fo;
        try {
            for (MultipartFile file : files) {
                tempFileName = "D:/tmp/" + file.getOriginalFilename();
                File tempFile = new File(tempFileName);
                tempFileNames.add(tempFileName);
                file.transferTo(tempFile);
                map.add("files", new FileSystemResource(tempFileName));
            }

            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
