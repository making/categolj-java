package am.ik.categolj.api.link;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

import am.ik.categolj.domain.model.Link;
import am.ik.categolj.domain.service.link.LinkService;

@Controller
@RequestMapping("links")
public class LinkRestController {
	@Inject
	protected LinkService linkService;

	@Inject
	protected LinkHelper linkHelper;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<LinkResponse> getLinks() {
		List<Link> links = linkService.findAll();
		Collection<LinkResponse> responses = Collections2.transform(links,
				new Function<Link, LinkResponse>() {
					@Override
					public LinkResponse apply(Link input) {
						return linkHelper.convert(input);
					}
				});
		return responses;
	}

}
