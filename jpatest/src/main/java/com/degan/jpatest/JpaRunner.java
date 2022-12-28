package com.degan.jpatest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.degan.jpatest.domain.Account;
import com.degan.jpatest.domain.Comment;
import com.degan.jpatest.domain.Post;
import com.degan.jpatest.domain.Study;
import com.degan.jpatest.repository.PostRepository;

@Component
@Transactional
public class JpaRunner implements ApplicationRunner{
    
    @Autowired
    PostRepository postRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        accountStudy();
        // postComment();
        // postRemove();
        // useRepository();
    }


    private void useRepository() {
        postRepository.findAll().forEach(System.out::println);
    }

    private void postRemove() {

        Session session = entityManager.unwrap(Session.class);
        Post post = session.get(Post.class, 1l);

        // CascadeType.REMOVE로 comment 설정하면
        // comments 모두 삭제 됨
        session.delete(post);
    }

    private void postComment() {
        Post post = new Post();
        post.setTitle("Spring Data JPA ...");

        Comment comment = new Comment();
        comment.setComment("aaaaa");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("soon");
        post.addComment(comment1);

        Session session = entityManager.unwrap(Session.class);
        session.save(post);
    }

    private void accountStudy() {
        Account account = new Account();
        account.setUsername("testuser");
        account.setPassword("passworda");

        Study study = new Study();
        study.setName("spring boot jpa study");

        account.addStudy(study);

        entityManager.persist(account);
        entityManager.persist(study);

        // JPA 안에 있는 hibernate 메소드를 unwrap 해서 호출
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study);

        Account testuser = session.load(Account.class, account.getId());
        testuser.setUsername("aaa");
        testuser.setUsername("aaa2");
        testuser.setUsername("testuser");

        System.out.println("=================");
        System.out.println(testuser.getUsername());
    }
}
