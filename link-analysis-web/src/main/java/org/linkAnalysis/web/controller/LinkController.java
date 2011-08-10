package org.linkAnalysis.web.controller;

import org.linkAnalysis.model.dao.Dao;
import org.linkAnalysis.model.entity.Link;
import org.linkAnalysis.model.search.LinkSearchCriteria;
import org.linkAnalysis.service.LinkService;
import org.linkAnalysis.service.util.ServiceResult;
import org.linkAnalysis.service.util.ValidationResult;
import org.linkAnalysis.web.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Pavel Karpukhin
 */
@Controller
@RequestMapping(value = "/link")
public class LinkController extends AbstractController {

    private LinkService linkService;

    /**
     * Constructs MVC controller with objects injected via autowiring
     *
     * @param linkService an object that provides actions on {@link Link} entity
     */
    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    /**
     * Handles GET request and produces page with all links
     *
     * @return {@link ModelAndView} with view name as linkList
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("linkList");
    }

    @RequestMapping(value = "/table")
    public ModelAndView table(LinkSearchCriteria searchCriteria) {
        return new ModelAndView("linkTable", "linkList", linkService.getLinksByCriteria(searchCriteria));
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView("linkAdd");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult add(final Link link) {
        validationResult = new ValidationResult();

        executeServiceMethod(new Invokable<Link>() {
            @Override
            public ServiceResult<Link> invoke() {
                return linkService.create(link);
            }
        });
        return ajaxResult();
    }

    @RequestMapping(value = "/analyze", method = RequestMethod.GET)
    public ModelAndView analyze(int id) {
        Link link = linkService.get(id);
        return new ModelAndView("linkAnalyze", "link", link);
    }

    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult analyze(final String method, final int linkId) {
        validationResult = new ValidationResult();

        if (method == null || method.isEmpty()) {
            validationResult.addError("Choose the method");
        } else {
            final Link link = new Link();
            link.setId(linkId);
            executeServiceMethod(new Invokable<Link>() {
                @Override
                public ServiceResult<Link> invoke() {
                    return linkService.analyzeLink(link, method);
                }
            });
        }

        return ajaxResult();
    }
}
