package com.backend.functional;

import com.backend.core.domain.Post;
import com.backend.rest.controller.fixture.RestDataFixture;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

import static junit.framework.TestCase.*;


/**
 * Created by alan on 2014-06-11.
 */
public class PostTests {

    static HttpHeaders getHeaders(String auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
        headers.add("Authorization", "Basic " + new String(encodedAuthorisation));

        return headers;
    }

    @Before
    public void setup() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void thatPostsCanBeAddedAndQueried() {
        HttpEntity<String> requestEntity = new HttpEntity<String>(
                RestDataFixture.standardPostJSON(), getHeaders("letsnosh" + ":" + "noshing"));

        RestTemplate template = new RestTemplate();
        ResponseEntity<Post> entity = template.postForEntity(
                "http://localhost:8080/posts", requestEntity, Post.class);

        String path = entity.getHeaders().getLocation().getPath();

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertTrue(path.startsWith("/posts/"));
        Post post = entity.getBody();

        System.out.println ("The Post ID is " + post.getUid());
        System.out.println ("The Location is " + entity.getHeaders().getLocation());

        assertEquals(RestDataFixture.MY_POST, post.getText());
    }

    @Test
    public void thatPostsCannotBeAddedAndQueriedWithBadUser() {

        HttpEntity<String> requestEntity = new HttpEntity<String>(
                RestDataFixture.standardPostJSON(),
                getHeaders("letsnosh" + ":" + "BADPASSWORD"));

        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<Post> entity = template.postForEntity(
                    "http://localhost:8080/posts",
                    requestEntity, Post.class);

            fail("Request Passed incorrectly with status " + entity.getStatusCode());
        } catch (HttpClientErrorException ex) {
            assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
        }
    }

    @Test
    public void thatPostsHaveCorrectHateoasLinks() {

        ResponseEntity<Post> entity = createPost();

        Post post = entity.getBody();

        String postBase = "/posts/" + post.getUid();

        assertEquals(entity.getHeaders().getLocation().toString(), post.getLink("self").getHref());
    }

    private ResponseEntity<Post> createPost() {
        HttpEntity<String> requestEntity = new HttpEntity<String>(
                RestDataFixture.standardPostJSON(), getHeaders("letsnosh" + ":" + "noshing"));

        RestTemplate template = new RestTemplate();
        return template.postForEntity(
                "http://localhost:8080/posts",
                requestEntity, Post.class);
    }

}
