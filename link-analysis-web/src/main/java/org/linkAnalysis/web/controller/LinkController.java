package org.linkAnalysis.web.controller;

import org.linkAnalysis.model.search.LinkSearchCriteria;
import org.linkAnalysis.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Pavel Karpukhin
 */
@Controller
public class LinkController {

    private LinkService linkService;

    /**
     * Constructs MVC controller with objects injected via autowiring
     *
     * @param linkService an obejct taht provides actions on {@link Link} entity
     */
    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    /**
     * Handles GET request and produces page with all links
     *
     * @param searchCriteria search criteria
     * @return {@link ModelAndView} with view name as linkList
     */
    @RequestMapping(value = "/link/list", method = RequestMethod.GET)
    public ModelAndView list(LinkSearchCriteria searchCriteria) {
        return new ModelAndView("linkList", "linkList", linkService.getLinksByCriteria(searchCriteria));
    }

    @RequestMapping(value = "/link/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("linkAdd");
    }
}
