package com.scoreboard.controller;

import com.scoreboard.model.ScoreboardEntry;
import com.scoreboard.service.ScoreboardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scoreboard")
public class ScoreboardController {
private final ScoreboardService service;

public ScoreboardController(ScoreboardService service) { this.service = service; }

@GetMapping
public List<ScoreboardEntry> getAll() { return service.findAll(); }

@GetMapping("/{id}")
public ResponseEntity<ScoreboardEntry> getById(@PathVariable Long id) {
return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}

@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public ScoreboardEntry create(@Valid @RequestBody ScoreboardEntry entry) { return service.save(entry); }

@PutMapping("/{id}")
public ResponseEntity<ScoreboardEntry> update(@PathVariable Long id, @Valid @RequestBody ScoreboardEntry entry) {
return service.update(id, entry).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}

@PatchMapping("/{id}")
public ResponseEntity<ScoreboardEntry> patch(@PathVariable Long id, @RequestBody ScoreboardEntry partial) {
return service.findById(id).map(existing -> {
if (partial.getName() != null) existing.setName(partial.getName());
if (partial.getColor() != null) existing.setColor(partial.getColor());
if (partial.getWeight() != null) existing.setWeight(partial.getWeight());
return ResponseEntity.ok(service.save(existing));
}).orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
return service.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
}
}
