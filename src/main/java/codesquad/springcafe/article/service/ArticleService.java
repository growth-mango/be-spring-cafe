package codesquad.springcafe.article.service;

import codesquad.springcafe.article.dto.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article createArticle(Article article);

    Optional<Article> findArticleById(Long id);

    List<Article> findAllArticles();
}
