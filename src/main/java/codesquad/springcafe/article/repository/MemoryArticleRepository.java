package codesquad.springcafe.article.repository;

import codesquad.springcafe.article.model.Article;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryArticleRepository implements ArticleRepository{
    private final Map<Long, Article> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong();

    @Override
    public Article save(Article article) {
        Long createdId = sequence.incrementAndGet();
        article.setId(createdId);
        store.put(createdId, article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Article> findAll() {
        return store.values().stream().toList();
    }

    @Override
    public void update(Article article) {

    }
}
