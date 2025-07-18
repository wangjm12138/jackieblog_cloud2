package com.jackie.blog.article;

import com.jackie.blog.article.dao.TagServiceMapper;
import com.jackie.blog.article.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

//@Slf4j
@SpringBootTest
class BlogArticleApplicationTests {
    static final Logger logger = LogManager.getLogger(BlogArticleApplicationTests.class);
    @Value("${server.port}")
    private String port;

    @Autowired
    private TagServiceMapper tagMapper;

    @Autowired
    private CategoryService categoryService;

    @Test
    void contextLoads() {

        //List<Tag> tags = tagMapper.selectList(null);
       // System.out.println(tags);
//        Result result = categoryService.listCategory();
//        System.out.println(result);
//        logger.trace("I am trace log.");
//        logger.debug("I am debug log.");
//        logger.warn("I am warn log.");
//        logger.error("I am error log.");
//        System.out.println(port);
    }

}
