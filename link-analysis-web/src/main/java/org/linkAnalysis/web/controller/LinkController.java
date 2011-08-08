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

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView linkList() {
        LinkSearchCriteria searchCriteria = new LinkSearchCriteria();
        return new ModelAndView("linkList", "linkList", linkService.getLinksByCriteria(searchCriteria));
    }
}
