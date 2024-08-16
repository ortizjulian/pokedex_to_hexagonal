package com.pokedex.pokedex_to_hexagonal.infrastrucutre.input.rest;


import com.pokedex.pokedex_to_hexagonal.application.dto.PokedexRequestDto;
import com.pokedex.pokedex_to_hexagonal.application.dto.PokedexResponseDto;
import com.pokedex.pokedex_to_hexagonal.application.handler.IPokedexHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pokedex")
@RequiredArgsConstructor
public class PokedexRestController {

    private final IPokedexHandler pokedexHandler;

    @PostMapping
    public ResponseEntity<Void> savePokemonInPokedex(@RequestBody PokedexRequestDto pokedexRequestDto){
        pokedexHandler.savePokemonInPokedex(pokedexRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PokedexResponseDto>> getAllPokemonFromPokedex(){
        return ResponseEntity.ok(pokedexHandler.getAllPokemonFromPokedex());
    }

    @GetMapping("/{number}")
    public ResponseEntity<PokedexResponseDto> getPokemonFromPokedex(@PathVariable(name = "number") Long pokemonNumber){
        return ResponseEntity.ok(pokedexHandler.getPokemonFromPokedex(pokemonNumber));
    }

    @PutMapping
    public ResponseEntity<Void> updatePokemonInPokedex(@RequestBody PokedexRequestDto pokedexRequest) {
        pokedexHandler.updatePokemonInPokedex(pokedexRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{pokemonNumber}")
    public ResponseEntity<Void> deletePokemonFromPokedex(@PathVariable Long pokemonNumber) {
        pokedexHandler.deletePokemonFromPokedex(pokemonNumber);
        return ResponseEntity.noContent().build();
    }


}
