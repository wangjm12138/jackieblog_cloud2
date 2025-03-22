package com.jackie.jackieblog.article.utils;

///*import com.google.common.base.Joiner;
//import com.vladsch.flexmark.ast.Node;
//import com.vladsch.flexmark.html.HtmlRenderer;
//import com.vladsch.flexmark.parser.Parser;
//import com.vladsch.flexmark.util.options.DataHolder;
//import com.vladsch.flexmark.util.options.MutableDataSet;
//import org.apache.commons.lang3.StringUtils;
//import org.jackieblog.jackieblog.model.vo.Result;*/
//import org.pegdown.PegDownProcessor;


//import java.io.*;
//import java.util.List;
//import java.util.StringJoiner;
//import java.util.stream.Collectors;

/**
 * @Author: Jackie Wang
 * @WechatID: ilovepython12138
 * @GitHub: https://github.com/wangjm12138
 * @Blog: http://www.jackieblog.top
 * @Date: 2023年03月21日 18:41
 * @Description:
 */
public class FileUtil {

//    public static String md2Html(String path, String imgaddr) throws IOException {
//        String html = null;
//        FileReader r = new FileReader(path);
//        char[] cbuf = new char[1024];
//        while( r.read(cbuf) != -1){
//            html += new String(cbuf);
//        }
//
//        PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
//        html = pdp.markdownToHtml(html);
//
//        if(!StringUtils.isEmpty(imgaddr)){
//            //将html中路径"assets/***" 变为 "http://localhost:4000/assets/"
//            String newHtml = StringUtils.replace(html, "assets/", imgaddr);
//            return newHtml;
//        }
//
//        return html;
//    }

//    public String md2Html2(String path, String imgaddr) throws IOException {
//        String mdStr = this.markdown2Html(path);
//        return mdStr;
//    }

//    public static String markdown2Html(String mdPath, String serverImgAddr) throws IOException {
////        InputStream steam = this.getClass().getClassLoader().getResourceAsStream(path);
//        String md = "";
//        FileReader r = new FileReader(mdPath);
//        char[] cbuf = new char[1024];
//
//        while( r.read(cbuf) != -1){
//            md += new String(cbuf).trim();
//        }
//        //System.out.println(md);
//
//        MutableDataSet options = new MutableDataSet();
//
//        Parser parser = Parser.builder(options).build();
//        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
//
//        Node document = parser.parse(md);
//        String html = renderer.render(document);
//
//        System.out.println(html);
//        if(!StringUtils.isEmpty(serverImgAddr)){
//            //将html中路径"assets/***" 变为 "http://localhost:4000/assets/"
//            String newHtml = StringUtils.replace(html, "/assets/", serverImgAddr);
//            return newHtml;
//        }
//        return html;
//
//    }

//    public String insertMD(String mdPath, String serverImgAddr) {
//
//
//    }
}
