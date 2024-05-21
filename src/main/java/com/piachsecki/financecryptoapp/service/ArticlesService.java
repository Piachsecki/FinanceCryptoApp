package com.piachsecki.financecryptoapp.service;

import com.piachsecki.financecryptoapp.dao.ArticlesDAO;
import com.piachsecki.financecryptoapp.domain.Article;
import com.piachsecki.financecryptoapp.domain.Customer;
import com.piachsecki.financecryptoapp.exception.NotExistingArticleIdException;
import com.piachsecki.financecryptoapp.exception.NotExistingCustomerException;
import com.piachsecki.financecryptoapp.repository.ArticlesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class ArticlesService implements ArticlesDAO {
    private final ArticlesRepository articlesRepository;
    private final CustomerService customerService;

    @Value(value = "${API_ARTICLES_KEY}")
    private String apiKey;

    @Value(value = "${API_ARTICLES_BASE_URL}")
    private String baseUrl;

    private final RestTemplate restTemplate;
    @Override
    public void addArticleToCustomer(Long customerId, Long articleId) {
        Customer customerById = customerService.findCustomerById(customerId);
        if(Objects.isNull(customerById)){
            log.error("Customer with the given ID{} does not exist!", customerId);
            throw new NotExistingCustomerException(
                    String.format("Customer with the given ID[%s] does not exist!", customerId)
            );
        }
        Article article = articlesRepository.findById(articleId).orElseThrow(() -> new NotExistingArticleIdException(
                String.format("Article with the given ID[%s] does not exist!", articleId)

        ));
        article.getCustomers().add(customerById);
        customerById.getFavouriteArticles().add(article);
        Article savedArticle = articlesRepository.save(article);
        log.info("Successfully added article with the id{} to the database", savedArticle.getId());

    }

    @Override
    public void deleteArticle(Long customerId, Long articleId) {
        Customer customerById = customerService.findCustomerById(customerId);
        if(Objects.isNull(customerById)){
            log.error("Customer with the given ID{} does not exist!", customerId);
            throw new NotExistingCustomerException(
                    String.format("Customer with the given ID[%s] does not exist!", customerId)
            );
        }

        articlesRepository.deleteById(articleId);
        log.info("Successfully deleted article with the id{} from the database",articleId);

    }

    @Override
    public Article findArticleForCustomerById(Long customerId, Long articleId) {
        Customer customerById = customerService.findCustomerById(customerId);
        if(Objects.isNull(customerById)){
            log.error("Customer with the given ID{} does not exist!", customerId);
            throw new NotExistingCustomerException(
                    String.format("Customer with the given ID[%s] does not exist!", customerId)
            );
        }
        return articlesRepository.findById(articleId).orElse(null);
    }

    @Override
    public List<Article> findAllArticlesForCustomer(Long customerId) {
        Customer customerById = customerService.findCustomerById(customerId);
        if(Objects.isNull(customerById)){
            log.error("Customer with the given ID{} does not exist!", customerId);
            throw new NotExistingCustomerException(
                    String.format("Customer with the given ID[%s] does not exist!", customerId)
            );
        }
        return customerService.findCustomerById(customerId).getFavouriteArticles();


    }

    public List<Article> getArticlesFromTheSpecifiedFields(List<String> fields){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        String url = baseUrl + "?industries=" + String.join(",", fields.stream().toArray(String[]::new)) + "&api_token=" + apiKey;

        final HttpEntity<String> headersEntity = new HttpEntity<>(headers);

        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.GET, headersEntity, java.util.Map.class);
        JSONObject jsonObject;
        List<Article> savedArticles = new ArrayList<>();
        if (exchange.getStatusCode() == HttpStatus.OK) {

            jsonObject = new JSONObject(Objects.requireNonNull(exchange.getBody()));
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for(int i=0; i<jsonArray.length(); i++) {


                Article article = Article.builder()
                        .title((String) jsonArray.getJSONObject(i).get("title"))
                        .webPage((String) jsonArray.getJSONObject(i).get("source"))
                        .url((String) jsonArray.getJSONObject(i).get("url"))
                        .build();

                Article savedArticle = articlesRepository.save(article);
                savedArticles.add(savedArticle);
            }


        }
        return savedArticles;

    }

}
