package org.linkAnalysis.model.dao.hibernate;

import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.linkAnalysis.model.dao.LinkDao;
import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.LinkSearchCriteria;
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
public class LinkHibernateDaoTest
        extends AbstractTransactionalTestNGSpringContextTests {

    private static final String SOME_HOST = "http://www.somehost.com";
    private static final String SOME_NEW_HOST = "http://www.somenewhost.com";
    private static final int INVALID_LINK_ID = -567890;

    @Autowired
    private LinkDao dao;
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @BeforeMethod
    public void beforeMethod() {
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void testCreateLink() {
        Link link = new Link();
        link.setUrl(SOME_HOST);

        dao.create(link);

        session.evict(link);
        assertNotEquals(link.getId().longValue(), 0, "Id wasn't created");
        assertNotNull(link.getVersion(), "Version wasn't created");
        assertTrue(link.getActive().booleanValue(), "Active wasn't created");

        Link result = (Link) session.get(Link.class, link.getId());

        assertReflectionEquals(link, result);
    }

    @Test(expectedExceptions = PropertyValueException.class)
    public void testCreateLinkWithNotNullViolation() {
        Link link = new Link();

        dao.create(link);
    }

    @Test
    public void testGetLink() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        Link result = dao.get(link.getId());

        assertNotNull(result);
        assertReflectionEquals(result, link);
    }

    @Test
    public void testGetLinkWithInvalidId() {
        Link result = dao.get(INVALID_LINK_ID);

        assertNull(result);
    }

    @Test
    public void testUpdateLink() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        link.setUrl(SOME_NEW_HOST);
        dao.update(link);
        session.flush();

        session.evict(link);
        assertEquals(link.getVersion().intValue(), 1);

        Link result = (Link) session.get(Link.class, link.getId());
        assertReflectionEquals(link, result);
    }

    @Test(expectedExceptions = PropertyValueException.class)
    public void testUpdateLinkWithNotNullViolation() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        link.setUrl(null);
        dao.update(link);
        session.flush();
    }

    @Test
    public void testDeleteLink() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        dao.delete(link);
        session.flush();

        session.evict(link);
        assertEquals(link.getActive().booleanValue(), false);

        Link result = (Link) session.get(Link.class, link.getId());
        assertReflectionEquals(link, result);
    }

    @Test
    public void testGetLinksByCriteria() {
        Link link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_HOST);
        session.save(link);

        link = new Link();
        link.setCreationDate(new DateTime());
        link.setActive(true);
        link.setUrl(SOME_NEW_HOST);
        session.save(link);

        LinkSearchCriteria searchCriteria = new LinkSearchCriteria();
        SearchResult<Link> result = dao.getLinksByCriteria(searchCriteria);

        assertEquals(result.getPageCount(), 1);
        assertEquals(result.getResult().size(), 2);
    }
}
