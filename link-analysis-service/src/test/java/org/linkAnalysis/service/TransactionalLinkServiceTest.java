package org.linkAnalysis.service;

import org.linkAnalysis.model.dao.LinkDao;
import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.service.transactional.TransactionalLinkService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

/**
 * @author Pavel Karpukhin
 */
public class TransactionalLinkServiceTest {

    private final static int LINK_ID = 33;

    private LinkDao linkDao;
    private LinkService linkService;

    @BeforeMethod
    public void setUp() {
        linkDao = mock(LinkDao.class);
        TransactionalLinkService transactionalLinkService =
                new TransactionalLinkService();
        transactionalLinkService.setLinkDao(linkDao);
        linkService = transactionalLinkService;
    }

    @Test
    public void testGetLink() {
        Link link = new Link();
        link.setId(LINK_ID);

        when(linkDao.get(LINK_ID)).thenReturn(link);

        Link foundLink = linkService.get(LINK_ID);

        assertNotNull(foundLink);
        assertEquals(foundLink.getId().intValue(), LINK_ID);

        verify(linkDao).get(LINK_ID);
    }

    @Test
    public void testGetLinksByCriteria() {
        //fail("Implement this test");
    }
}
