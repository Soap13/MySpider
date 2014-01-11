package com.my.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParse {

	private Logger log = Logger.getLogger(HtmlParse.class);

	/***/
	private Parser parser;
    private Integer deep;
    
	/** 获取输入流 */
	public HtmlParse(InputStream ins,Integer deep) {
		log.info("\n---获取输入流,解析开始---");
		this.deep=deep+1;
		parser = new Parser();
		try {
			//parser.setEncoding("gbk");
			parser.setInputHTML(getContent(ins));
		} catch (ParserException | UnsupportedEncodingException e) {
			log.info("\n---添加解析内容失败---");
			e.printStackTrace();
		}
	}

	public HtmlParse(String url){
		log.info("\n---获取输入流,解析开始---"+url);
		this.deep=1;
		parser = new Parser();
		try {
			//parser.setEncoding("gbk");
			parser.setURL(url);
		} catch (ParserException e) {
			log.info("\n---添加解析内容失败---");
			e.printStackTrace();
		}
	}
	/** 得到内容 
	 * @throws UnsupportedEncodingException */
	private String getContent(InputStream ins) throws UnsupportedEncodingException {
		log.info("\n---获取输入流,获取内容---");
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(ins,"gb2312"));
		String s = "";
		try {
			while ((s = br.readLine()) != null) {
				sb.append(new String(s.getBytes()));
			}
		} catch (IOException e) {
			log.info("\n---获取输入流,获取失败---");
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		log.info("\n---获取输入流,获取结束---");
		return sb.toString();
	}

	/** 用正确的编码 转码 字符 */
//	public void changeCharset() {
//		log.info("\n<===web网页转码开始===>");
//		return;
//		if (MyUtil.isRealString(charset)) {
//			try {
//				webString = new String(webString.getBytes(), charset);
//				
//				System.err.println(webString);
//				log.info("\n<===web网页转码结束===>");
//			} catch (UnsupportedEncodingException e) {
//				log.info("\n<===web网页转码失败===>");
//				e.printStackTrace();
//			}
//		}
//	}
	
	/**
	 * 抽取出所有超链接
	 * @return Set<String>
	 */
	public Set<String> getCurrentUrl(){
		log.info("\n---抽取超链接开始了---");
		Set<String> urlSet=new HashSet<>();
		NodeFilter nf=new TagNameFilter("a");
		NodeList ns=null;
		Node n=null;
		LinkTag lt=null;
		int count=0;
		try {
			ns = parser.extractAllNodesThatMatch(nf);
			for(int i=0;i<ns.size();i++){
				n=ns.elementAt(i);
				if(n instanceof LinkTag){
					count++;
					lt=(LinkTag)n;
					urlSet.add(lt.getLink());
					//log.info("\n---"+lt.getLink());
				}
			}
		} catch (ParserException e) {
			log.info("\n---超链接解析出错---");
			e.printStackTrace();
		}
		log.info("\n---发现的连接数:"+count);
		return urlSet;
	}
	
	/**
	 * 获取内容信息
	 * 通过配置获得得到想系的内容
	 */
	public void getContent(){
	 	log.info("\n---获取具体内容开始---");
	 	 NodeFilter filter = new HasAttributeFilter( "class", "product" );
			try {
				 NodeList nodes = parser.extractAllNodesThatMatch(filter);
				 Parser p=new Parser();
				 if(nodes.size()>0){
				    	Node node=nodes.elementAt(0);
				    //2.单独构建内容模块
				    	p.setInputHTML(node.toHtml().toString());
				    	//p.setResource(node.toHtml().toString());
				    //3.每个产品模块抽取	
				    	NodeFilter nf=new TagNameFilter("ul");
				    	NodeList ns=p.extractAllNodesThatMatch(nf);
				    	
				    	log.error("\n---获得信息数是："+ns.size());
				    	
				    	for(int j=0;j<ns.size();j++){
				    		 Node n=ns.elementAt(j);
				    	     NodeList nl=n.getChildren();	 
				    //4.细节内容获取
				    	     for(Node nn:nl.toNodeArray()){
				    	    log.info("\n---"+nn.toPlainTextString());
				    	     }
				          log.info("\n---------------------------");
				    	 }
				     }
			} catch (ParserException e) {
				e.printStackTrace();
			}
	 	
	 	log.info("\n---获取具体内容结束---");
	}
	
	/**得到深度*/
	public Integer getDeep(){
		log.info("\n---返回深度---");
		return this.deep;
	}
}
