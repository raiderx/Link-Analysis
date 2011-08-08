package org.linkAnalysis.model.dao.hibernate;

import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.linkAnalysis.model.dao.ImageDao;
import org.linkAnalysis.model.entity.Image;
import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.ImageSearchCriteria;
import org.linkAnalysis.model.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * @author Pavel Karpukhin
 */
@ContextConfiguration(locations = {"classpath:/org/linkAnalysis/model/entity/applicationContext-dao.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ImageHibernateDaoTest
        extends AbstractTransactionalTestNGSpringContextTests {

    private static final String SOME_HOST = "http://www.somehost.com";
    private static final String SOME_URL = "http://www.somehost.com/someimage.png";
    private static final String SOME_NEW_URL = "http://www.somehost.com/somenewimage.png";
    private static final int INVALID_IMAGE_ID = -567890;

    @Autowired
    private ImageDao dao;
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @BeforeMethod
    public void beforeMethod() {
        session = sessionFactory.getCurrentSession();
    }
    @Test
    public void testCreateImage() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        Image image = new Image();
        image.setLink(link);
        image.setUrl(SOME_URL);
        image.setDownloaded(false);

        dao.create(image);

        session.evict(image);
        assertNotEquals(image.getId().longValue(), 0, "Id wasn't created");
        assertNotNull(image.getVersion(), "Version wasn't created");
        assertTrue(image.getActive().booleanValue(), "Active wasn't created");

        Image result = (Image) session.get(Image.class, image.getId());

        assertReflectionEquals(image, result);
    }

    @Test(expectedExceptions = PropertyValueException.class)
    public void testCreateImageWithNotNullViolation() {
        Image image = new Image();

        dao.create(image);
    }

    @Test
    public void testGetImage() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        Image image = new Image();
        image.setCreationDate(new DateTime());
        image.setActive(true);
        image.setLink(link);
        image.setUrl(SOME_URL);
        image.setDownloaded(false);
        session.save(image);

        Image result = dao.get(image.getId());

        assertNotNull(result);
        assertReflectionEquals(result, image);
    }

    @Test
    public void testGetImageWithInvalidId() {
        Image result = dao.get(INVALID_IMAGE_ID);

        assertNull(result);
    }
    @Test
    public void testUpdateImage() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        Image image = new Image();
        image.setCreationDate(new DateTime());
        image.setActive(true);
        image.setLink(link);
        image.setUrl(SOME_URL);
        image.setDownloaded(false);
        session.save(image);

        image.setUrl(SOME_NEW_URL);
        dao.update(image);
        session.flush();

        session.evict(image);
        assertEquals(image.getVersion().intValue(), 1);

        Image result = (Image) session.get(Image.class, image.getId());
        assertReflectionEquals(image, result);
    }

    @Test(expectedExceptions = PropertyValueException.class)
    public void testUpdateImageWithNotNullViolation() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        Image image = new Image();
        image.setCreationDate(new DateTime());
        image.setActive(true);
        image.setLink(link);
        image.setUrl(SOME_URL);
        image.setDownloaded(false);
        session.save(image);

        image.setUrl(null);
        dao.update(image);
        session.flush();
    }

    @Test
    public void testDeleteImage() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        Image image = new Image();
        image.setCreationDate(new DateTime());
        image.setActive(true);
        image.setLink(link);
        image.setUrl(SOME_URL);
        image.setDownloaded(false);
        session.save(image);

        dao.delete(image);
        session.flush();

        session.evict(image);
        assertEquals(image.getActive().booleanValue(), false);

        Image result = (Image) session.get(Image.class, image.getId());
        assertReflectionEquals(image, result);
    }
    @Test
    public void testGetLinksByCriteria() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        Image someImage = new Image();
        someImage.setCreationDate(new DateTime());
        someImage.setActive(true);
        someImage.setLink(link);
        someImage.setUrl(SOME_URL);
        someImage.setDownloaded(false);
        session.save(someImage);

        Image someNewImage = new Image();
        someNewImage.setCreationDate(new DateTime());
        someNewImage.setActive(true);
        someNewImage.setLink(link);
        someNewImage.setUrl(SOME_NEW_URL);
        someNewImage.setDownloaded(false);
        session.save(someNewImage);

        ImageSearchCriteria searchCriteria = new ImageSearchCriteria();
        Image image = new Image();
        image.setUrl("someimage");
        searchCriteria.setExample(image);
        SearchResult<Image> result = dao.getImagesByCriteria(searchCriteria);

        assertEquals(result.getPageCount(), 1);
        assertEquals(result.getResult().size(), 1);
        assertReflectionEquals(someImage, result.getResult().get(0));

        image = new Image();
        image.setUrl("somenewimage");
        searchCriteria.setExample(image);
        result = dao.getImagesByCriteria(searchCriteria);

        assertEquals(result.getPageCount(), 1);
        assertEquals(result.getResult().size(), 1);
        assertReflectionEquals(someNewImage, result.getResult().get(0));
    }
}
