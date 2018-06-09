package com.luolh.blog.entities;

import java.util.Date;

/**
 * 博客类
 * @author LuoLH
 * @since 20180528
 */
public class Blog {
	/**
	 * 博客id
	 * */
	private int blogId;
	
	/**
	 * 博客标题
	 * */
	private String blogTitle;
	
	/**
	 * 发表时间
	 * */
	private Date createTime;
	
	/**
	 * 内容
	 * */
	private String content;
	
	/**
	 * 类型
	 * */
	private String blogType;
	/**
	 * 访问次数
	 * */
	private long visitTimes;
	
	public Blog() {
		super();
	}

	public Blog(int blogId, String blogTitle, Date createTime, String content, String blogType, Long visitTimes) {
		super();
		this.blogId = blogId;
		this.blogTitle = blogTitle;
		this.createTime = createTime;
		this.content = content;
		this.blogType = blogType;
		this.visitTimes = visitTimes;
	}

	/**
	 * @return the blogId
	 */
	public int getBlogId() {
		return blogId;
	}

	/**
	 * @param blogId the blogId to set
	 */
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	/**
	 * @return the blogTitle
	 */
	public String getBlogTitle() {
		return blogTitle;
	}

	/**
	 * @param blogTitle the blogTitle to set
	 */
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the blogType
	 */
	public String getBlogType() {
		return blogType;
	}

	/**
	 * @param blogType the blogType to set
	 */
	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}

	/**
	 * @return the visitTimes
	 */
	public long getVisitTimes() {
		return visitTimes;
	}

	/**
	 * @param visitTimes the visitTimes to set
	 */
	public void setVisitTimes(long visitTimes) {
		this.visitTimes = visitTimes;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogTitle=" + blogTitle + ", createTime=" + createTime + ", content="
				+ content + ", blogType=" + blogType + ", visitTimes=" + visitTimes + "]";
	}

}
