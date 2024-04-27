package codesquad.springcafe.article.service;

import codesquad.springcafe.article.model.Article;
import codesquad.springcafe.user.model.User;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article createArticle(Article article);

    Optional<Article> findArticleById(Long id);

    List<Article> findAllArticles();
    Article update(Long id, User currentUserId, Article article);
}
