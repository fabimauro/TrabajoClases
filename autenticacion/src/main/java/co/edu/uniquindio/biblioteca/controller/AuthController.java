package co.edu.uniquindio.biblioteca.controller;

import co.edu.uniquindio.biblioteca.dto.LoginDTO;
import co.edu.uniquindio.biblioteca.dto.Respuesta;
import co.edu.uniquindio.biblioteca.dto.TokenDTO;
import co.edu.uniquindio.biblioteca.dto.service.LoginServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class AuthController {

    private final LoginServicio loginServicio;

    @PostMapping("/signin")
    public ResponseEntity<Respuesta<TokenDTO>> login(@RequestBody LoginDTO loginDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("Login correcto", loginServicio.login(loginDTO)) );
    }


    @PostMapping("/refresh")
    public ResponseEntity<Respuesta<TokenDTO>> refresh(@RequestBody TokenDTO token) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new Respuesta<>("", loginServicio.refresh(token)) );
    }

    /**
     *
     * @param newUserDTO
     * @return
     * @throws Exception

    @PostMapping("/signup")
    public ResponseEntity<Respuesta<String>> createUser(@RequestBody NewUserDTO newUserDTO) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new Respuesta<>(loginServicio.createUser(newUserDTO) ? "Creado correctamente": "Error", "") );
    }
     */
}
