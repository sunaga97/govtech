package com.example.demo.controller;

import com.example.demo.exception.CSVFormatException;
import com.example.demo.helper.CSVHelper;
import com.example.demo.message.ResponseMessage;
import com.example.demo.message.SuccessMessage;
import com.example.demo.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CSVController {

    @Autowired
    CSVService fileService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new SuccessMessage());
            }  catch (CSVFormatException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
            } catch (NumberFormatException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("The csv file contains incorrect datatype or missing value"));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("The csv file contains incorrect number of columns"));
            }
            catch (Exception e) {
                e.printStackTrace();
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
