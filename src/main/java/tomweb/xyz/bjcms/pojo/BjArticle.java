package tomweb.xyz.bjcms.pojo;

import java.util.Date;

public class BjArticle {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.id
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.article_id
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String articleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.app_id
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String appId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.nid
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String nid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.updated_at
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private Long updatedAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.type
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.status
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.collection
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String collection;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.title
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.article_url
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String articleUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.created_on
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private Date createdOn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.update_on
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private Date updateOn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.is_delete
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private Boolean isDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.abstract_txt
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String abstractTxt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.public_status
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private Integer publicStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.keyWords
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String keywords;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.description
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bj_article.article_body
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    private String articleBody;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.id
     *
     * @return the value of bj_article.id
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.id
     *
     * @param id the value for bj_article.id
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.article_id
     *
     * @return the value of bj_article.article_id
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.article_id
     *
     * @param articleId the value for bj_article.article_id
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.app_id
     *
     * @return the value of bj_article.app_id
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.app_id
     *
     * @param appId the value for bj_article.app_id
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.nid
     *
     * @return the value of bj_article.nid
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getNid() {
        return nid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.nid
     *
     * @param nid the value for bj_article.nid
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setNid(String nid) {
        this.nid = nid == null ? null : nid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.updated_at
     *
     * @return the value of bj_article.updated_at
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.updated_at
     *
     * @param updatedAt the value for bj_article.updated_at
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.type
     *
     * @return the value of bj_article.type
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.type
     *
     * @param type the value for bj_article.type
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.status
     *
     * @return the value of bj_article.status
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.status
     *
     * @param status the value for bj_article.status
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.collection
     *
     * @return the value of bj_article.collection
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getCollection() {
        return collection;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.collection
     *
     * @param collection the value for bj_article.collection
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setCollection(String collection) {
        this.collection = collection == null ? null : collection.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.title
     *
     * @return the value of bj_article.title
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.title
     *
     * @param title the value for bj_article.title
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.article_url
     *
     * @return the value of bj_article.article_url
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getArticleUrl() {
        return articleUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.article_url
     *
     * @param articleUrl the value for bj_article.article_url
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl == null ? null : articleUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.created_on
     *
     * @return the value of bj_article.created_on
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.created_on
     *
     * @param createdOn the value for bj_article.created_on
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.update_on
     *
     * @return the value of bj_article.update_on
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public Date getUpdateOn() {
        return updateOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.update_on
     *
     * @param updateOn the value for bj_article.update_on
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.is_delete
     *
     * @return the value of bj_article.is_delete
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.is_delete
     *
     * @param isDelete the value for bj_article.is_delete
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.abstract_txt
     *
     * @return the value of bj_article.abstract_txt
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getAbstractTxt() {
        return abstractTxt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.abstract_txt
     *
     * @param abstractTxt the value for bj_article.abstract_txt
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setAbstractTxt(String abstractTxt) {
        this.abstractTxt = abstractTxt == null ? null : abstractTxt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.public_status
     *
     * @return the value of bj_article.public_status
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public Integer getPublicStatus() {
        return publicStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.public_status
     *
     * @param publicStatus the value for bj_article.public_status
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setPublicStatus(Integer publicStatus) {
        this.publicStatus = publicStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.keyWords
     *
     * @return the value of bj_article.keyWords
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.keyWords
     *
     * @param keywords the value for bj_article.keyWords
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.description
     *
     * @return the value of bj_article.description
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.description
     *
     * @param description the value for bj_article.description
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bj_article.article_body
     *
     * @return the value of bj_article.article_body
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public String getArticleBody() {
        return articleBody;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bj_article.article_body
     *
     * @param articleBody the value for bj_article.article_body
     *
     * @mbg.generated Thu Jul 16 21:50:26 CST 2020
     */
    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody == null ? null : articleBody.trim();
    }
}