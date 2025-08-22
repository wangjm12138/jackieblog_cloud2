package com.jackie.blog.article.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HtmlSanitizerService {

    // 使用OWASP HTML Sanitizer
    private static final PolicyFactory POLICY = new HtmlPolicyBuilder()
            .allowElements("p", "br", "b", "i", "u", "strong", "em", "ul", "ol", "li")
            .allowAttributes("href").onElements("a")
            .allowAttributes("src").onElements("img")
            .allowAttributes("alt").onElements("img")
            .allowUrlProtocols("https", "http")
            .requireRelNofollowOnLinks()  // 防止SEO垃圾
            .toFactory();

    /**
     * 净化HTML内容
     */
    public String sanitize(String html) {
        if (html == null || html.trim().isEmpty()) {
            return html;
        }

        try {
            String sanitized = POLICY.sanitize(html);
            log.debug("HTML净化: {} -> {}", html, sanitized);
            return sanitized;
        } catch (Exception e) {
            log.warn("HTML净化失败: {}", e.getMessage());
            // 净化失败时返回转义后的文本
            return escapeHtml(html);
        }
    }

    /**
     * 检查是否包含不安全内容
     */
    public boolean containsUnsafeContent(String html) {
        if (html == null) return false;

        String sanitized = POLICY.sanitize(html);
        // 如果净化后的内容与原内容不同，说明有被过滤的不安全内容
        return !html.equals(sanitized);
    }

    /**
     * 转义HTML特殊字符
     */
    public String escapeHtml(String text) {
        return StringEscapeUtils.escapeHtml4(text);
    }

    /**
     * 净化并截断（防止超长内容）
     */
    public String sanitizeAndTruncate(String html, int maxLength) {
        String sanitized = sanitize(html);
        if (sanitized.length() > maxLength) {
            return sanitized.substring(0, maxLength) + "...";
        }
        return sanitized;
    }
}