package am.ik.categolj.api.entry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import am.ik.categolj.domain.common.consts.Const;
import am.ik.categolj.domain.model.Entry;
import am.ik.categolj.domain.service.entry.EntrySearchService;
import am.ik.categolj.domain.service.entry.EntryService;

@Controller
@RequestMapping("entries")
public class EntryRestController {
	@Inject
	protected EntryService entryService;
	@Inject
	protected EntrySearchService entrySearchService;

	@Inject
	protected EntryHelper entryHelper;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Collection<EntryResponse> getEntries(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
		List<Entry> entries = entryService.getEntriesByPage(page,
				Const.VIEW_COUNT);

		// bean convert
		List<EntryResponse> responses = new ArrayList<>();
		for (Entry entry : entries) {
			EntryResponse response = entryHelper.convertEntry(entry);
			responses.add(response);
		}
		return responses;
	}

	@RequestMapping(params = "q", method = RequestMethod.GET)
	@ResponseBody
	public Collection<EntryResponse> searcgEntries(
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "q") String query) {

		Set<String> keywords = entrySearchService.splitQuery(query);
		List<Entry> entries = entrySearchService
				.getKeywordSearchedEntriesByPage(keywords, page,
						Const.VIEW_COUNT);

		// bean convert
		List<EntryResponse> responses = new ArrayList<>();
		for (Entry entry : entries) {
			EntryResponse response = entryHelper.convertEntry(entry);
			responses.add(response);
		}
		return responses;
	}

	@RequestMapping(value = "{entryId}", method = RequestMethod.GET)
	@ResponseBody
	public EntryResponse getEntry(@PathVariable("entryId") Long entryId) {
		Entry entry = entryService.getEntryById(entryId);
		// bean convert
		EntryResponse response = entryHelper.convertEntry(entry);
		return response;
	}
}
