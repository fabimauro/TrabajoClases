package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.LoginDTO;
import co.edu.uniquindio.biblioteca.dto.NewUserDTO;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.dto.TokenDTO;
import co.edu.uniquindio.biblioteca.service.LoginServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginServicio loginServicio;

    @PostMapping("/login")
    public ResponseEntity<Respuesta<TokenDTO>> login(@RequestBody LoginDTO loginDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Login correcto", loginServicio.login(loginDTO)) );
    }

    @PostMapping("/refresh")
    public ResponseEntity<Respuesta<TokenDTO>> refresh(@RequestBody TokenDTO token) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", loginServicio.refresh(token)) );
    }

    @PostMapping("/create-user")
    public ResponseEntity<Respuesta<String>> createUser(@RequestBody NewUserDTO newUserDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>(loginServicio.createUser(newUserDTO) ? "Creado correctamente": "Error", "") );
    }

    /*@PostMapping("/create-admin")
    public ResponseEntity<Respuesta<String>> createAdmin(@RequestBody NewUserDTO newUserDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>("NADA por ahora", ""));
    }*/
}
