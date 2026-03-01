package com.example.desafio.Controller;

import com.example.desafio.Service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/curriculo")
@CrossOrigin
public class CurriculoController {

    private final S3Service s3Service;

    public CurriculoController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("foto") MultipartFile file) {

        try {
            String url = s3Service.uploadFile(file);
            return ResponseEntity.ok(Map.of(
                    "mensagem", "Upload realizado com sucesso",
                    "urlImagem", url
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao enviar arquivo para o S3");
        }
    }
}
