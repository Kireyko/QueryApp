package com.kireiko.queryapp.html;

import java.util.List;

public class HtmlGenerator {
    private static final char HTML_LINE_SEPARATOR = '\n';

    public String generateHtml(List<String> headersList, List<List<String>> rowsList) {
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append(HTML_LINE_SEPARATOR);
        html.append("<table>");
        for (String header : headersList) {
            html.append("<th>" + header + "</td>");
        }
        html.append(System.lineSeparator());

        for (List<String> rows : rowsList) {
            html.append("<tr>");
            for (String value : rows) {
                html.append("<td>" + value + "</td>");
            }
            html.append("</tr>");
        }
        html.append("</table>");
        html.append("</body>" + "</html>");
        return html.toString();
    }
}
