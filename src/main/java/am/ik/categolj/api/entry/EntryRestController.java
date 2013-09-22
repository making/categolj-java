package am.ik.categolj.api.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
	public Page<EntryResponse> getEntries(
			@PageableDefault(size = Const.REST_VIEW_COUNT) Pageable pageable) {
		System.out.println(pageable);
		List<Entry> entries = entryService.getEntriesByPage(
				pageable.getPageNumber() + 1, pageable.getPageSize());
		int total = entryService.getTotalEntryCount();

		// bean convert
		List<EntryResponse> responses = new ArrayList<>();
		for (Entry entry : entries) {
			EntryResponse response = entryHelper.convertEntry(entry);
			responses.add(response);
		}
		Page<EntryResponse> page = new PageImpl<>(responses, pageable, total);
		return page;
	}

	@RequestMapping(params = "q", method = RequestMethod.GET)
	@ResponseBody
	public Page<EntryResponse> searcgEntries(
			@PageableDefault(size = Const.REST_VIEW_COUNT) Pageable pageable,
			@RequestParam(value = "q") String query) {

		Set<String> keywords = entrySearchService.splitQuery(query);
		List<Entry> entries = entrySearchService
				.getKeywordSearchedEntriesByPage(keywords,
						pageable.getPageNumber() + 1, pageable.getPageSize());
		int total = entrySearchService.getKeywordSearchedEntryCount(keywords);

		// bean convert
		List<EntryResponse> responses = new ArrayList<>();
		for (Entry entry : entries) {
			EntryResponse response = entryHelper.convertEntry(entry);
			responses.add(response);
		}
		Page<EntryResponse> page = new PageImpl<>(responses, pageable, total);
		return page;
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
