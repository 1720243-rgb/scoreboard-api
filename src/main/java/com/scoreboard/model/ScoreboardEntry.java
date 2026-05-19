package com.scoreboard.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ScoreboardEntry {
private Long id;

@NotBlank(message = "Name must not be blank")
private String name;

@NotBlank(message = "Color must not be blank")
private String color;

@NotNull(message = "Weight must not be null")
private Weight weight;

public ScoreboardEntry() {}

public ScoreboardEntry(Long id, String name, String color, Weight weight) {
this.id = id; this.name = name;
this.color = color; this.weight = weight;
}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getColor() { return color; }
public void setColor(String color) { this.color = color; }
public Weight getWeight() { return weight; }
public void setWeight(Weight weight) { this.weight = weight; }
}
