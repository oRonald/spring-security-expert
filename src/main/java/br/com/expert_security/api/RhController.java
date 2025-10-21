package br.com.expert_security.api;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rh")
public class RhController {

    @GetMapping("/tecnico")
    @PreAuthorize("hasAnyRole('TECNICO_RH','GERENTE_RH','ADMIN')")
    public ResponseEntity<String> tecnico(){
        return ResponseEntity.ok("Tecnico route");
    }

    @GetMapping("/gerente")
    @PreAuthorize("hasAnyRole('GERENTE_RH','ADMIN')")
    public ResponseEntity<String> gerente(){
        return ResponseEntity.ok("gerente route");
    }
}
