package codesquad.springcafe.repository;

import codesquad.springcafe.dto.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id);
    List<Article> findAll();
}