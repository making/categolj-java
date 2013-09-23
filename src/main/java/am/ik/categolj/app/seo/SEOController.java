package am.ik.categolj.app.seo;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class SEOController {
	@Value("${phantomjs.path}")
	protected String phantomjsPath;
	@Value("${phantomjs.script}")
	protected Resource phantomjsScript;

	private static final Logger logger = LoggerFactory
			.getLogger(SEOController.class);

	@RequestMapping(value = { "/" }, method = RequestMethod.GET, params = "_escaped_fragment_", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String renderByEscapedFragment(
			@RequestParam("_escaped_fragment_") String escapedFragment,
			HttpServletRequest request) throws IOException,
			InterruptedException {
		String url = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getRequestURI() + "#"
				+ escapedFragment;
		logger.warn("retrieve {}", url);
		String path = phantomjsScript.getURL().getPath();
		ProcessBuilder pb = new ProcessBuilder(phantomjsPath, path, url);
		Process process = pb.start();
		process.waitFor();
		try (InputStream inputStream = process.getInputStream()) {
			String contents = IOUtils.toString(inputStream);
			return contents;
		}
	}
}
