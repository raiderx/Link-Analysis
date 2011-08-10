package org.linkAnalysis.web.controller;

import org.linkAnalysis.model.search.ImageSearchCriteria;
import org.linkAnalysis.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 
 */
@Controller
@RequestMapping(value = "/image")
public class ImageController extends AbstractController {

    private ImageService imageService;

    /**
     * Constructs MVC controller with objects injected via autowiring
     *
     * @param imageService an object that provides actions on {@link Image} entity
     */
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Handles GET request and produces page with all images
     *
     * @return {@link ModelAndView} with view name as imageList
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("imageList");
    }

    @RequestMapping(value = "/table")
    public ModelAndView table(ImageSearchCriteria searchCriteria) {
        return new ModelAndView("imageTable", "imageList", imageService.getImagesByCriteria(searchCriteria));
    }
    
}
