package com.scoreboard.service;

import com.scoreboard.model.ScoreboardEntry;
import com.scoreboard.model.Weight;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ScoreboardService {
private final Map<Long, ScoreboardEntry> store = new ConcurrentHashMap<>();
private final AtomicLong idSequence = new AtomicLong(1);

public ScoreboardService() {
save(new ScoreboardEntry(null, "Alice", "Red", Weight.HEAVY));
}

public List<ScoreboardEntry> findAll() { return new ArrayList<>(store.values()); }

public Optional<ScoreboardEntry> findById(Long id) { return Optional.ofNullable(store.get(id)); }

public ScoreboardEntry save(ScoreboardEntry entry) {
if (entry.getId() == null) entry.setId(idSequence.getAndIncrement());
store.put(entry.getId(), entry);
return entry;
}

public Optional<ScoreboardEntry> update(Long id, ScoreboardEntry updated) {
if (!store.containsKey(id)) return Optional.empty();
updated.setId(id);
store.put(id, updated);
return Optional.of(updated);
}

public boolean delete(Long id) { return store.remove(id) != null; }
}
