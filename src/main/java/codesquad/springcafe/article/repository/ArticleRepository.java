package codesquad.springcafe.article.repository;

import codesquad.springcafe.article.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id);
    List<Article> findAll();
    void update(Article article);
}
