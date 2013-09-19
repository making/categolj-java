package am.ik.categolj.api.link;

import org.springframework.stereotype.Component;

import am.ik.categolj.domain.model.Link;

@Component
public class LinkHelper {

	protected LinkResponse convert(Link link) {
		LinkResponse response = new LinkResponse();
		response.setLinkName(link.getName());
		response.setUrl(link.getUrl());
		return response;
	}
}
