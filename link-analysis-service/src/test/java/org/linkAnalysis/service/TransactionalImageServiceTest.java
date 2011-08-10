package org.linkAnalysis.service;

import org.linkAnalysis.model.dao.ImageDao;
import org.linkAnalysis.model.entity.Image;
import org.linkAnalysis.service.transactional.TransactionalImageService;
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
public class TransactionalImageServiceTest {

    private final static int IMAGE_ID = 33;

    private ImageDao imageDao;
    private ImageService imageService;

    @BeforeMethod
    public void setUp() {
        imageDao = mock(ImageDao.class);
        TransactionalImageService transactionalImageService =
                new TransactionalImageService();
        transactionalImageService.setImageDao(imageDao);
        imageService = transactionalImageService;
    }

    @Test
    public void testGetImage() {
        Image image = new Image();
        image.setId(IMAGE_ID);

        when(imageDao.get(IMAGE_ID)).thenReturn(image);

        Image foundImage = imageService.get(IMAGE_ID);

        assertNotNull(foundImage);
        assertEquals(foundImage.getId().intValue(), IMAGE_ID);

        verify(imageDao).get(IMAGE_ID);
    }

    @Test
    public void testGetImagesByCriteria() {
        //fail("Implement this test");
    }
}
