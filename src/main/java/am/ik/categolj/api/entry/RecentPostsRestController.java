package am.ik.categolj.api.entry;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import am.ik.categolj.domain.common.consts.Const;
import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.service.entry.EntryService;

@Controller
@RequestMapping("recent_posts")
public class RecentPostsRestController {
	@Inject
	protected EntryService entryService;

	@Inject
	protected EntryHelper entryHelper;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<EntryResponse> getRecently() {
		List<Entry> entries = entryService
				.getEntriesOnlyIdTitle(Const.RECENT_COUNT);
		// bean convert
		List<EntryResponse> responses = new ArrayList<>();
		for (Entry entry : entries) {
			EntryResponse response = entryHelper.convertEntry(entry);
			responses.add(response);
		}
		return responses;
	}

}
