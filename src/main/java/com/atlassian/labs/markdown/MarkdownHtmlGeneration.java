package com.atlassian.labs.markdown;

/**
 * This interface allows the caller to get involved in the HTML generation
 * that is passed back to the pageDown JS
 */
public interface MarkdownHtmlGeneration
{
    String generateImageHTML(String sharedSecret, String url, String alt_text, String title);
    
    String generateLinkHTML(String sharedSecret, String url, String title, String linkText);
}