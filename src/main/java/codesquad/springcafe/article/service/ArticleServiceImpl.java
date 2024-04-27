package codesquad.springcafe.article.service;

import codesquad.springcafe.article.model.Article;
import codesquad.springcafe.article.repository.ArticleRepository;
import codesquad.springcafe.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article update(Long id, User currentUser, Article updateArticle) {
        // 수정할 아티클 조회하기
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));

        // 로그인한 사용자가 글쓴이와 같은지 확인하기
        if (!article.getWriter().equals(currentUser.getUserId())) {
            throw new IllegalStateException("본인의 글만 수정할 수 있습니다.");
        }

        // 업데이트한 내용 저장하기
        article.setTitle(updateArticle.getTitle());
        article.setContent(updateArticle.getContent());
        articleRepository.update(article);
        return article;
    }


}
