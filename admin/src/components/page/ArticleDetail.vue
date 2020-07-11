<template name="articleDetail">
    <div class="box">
        <div class="header-list">
            <span style="line-height: 60px;margin-left: 30px">爱旅游爱工作</span>
            <span style="line-height: 60px;margin-right: 30px;float: right"><a href="/" @click="logout()">退出</a></span>
        </div>
        <div class="container-editor" v-loading="save">
            <div class="editor">
                <div ref="editor" style="text-align:left"></div>
            </div>
            <div class="cover">
                <div style="margin-top: 20px">
                    <div style="height: 20px"></div>
                    <el-form label-position="right" label-width="80px" :model="article">
                        <el-form-item label="标题">
                            <!--                        <el-input v-model="article.title" type="textarea" :rows="2"></el-input>-->
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="请输入内容"
                                    v-model="article.title">
                            </el-input>

                        </el-form-item>
                        <el-form-item label="封面">
                            <el-radio v-model="coverCount" :label="1">单图</el-radio>
                            <el-radio v-model="coverCount" :label="3">三图</el-radio>

                            <div class="cove-upload">
                                <div @click="uploadArticle(0)"
                                     style="height: 100px;width: 100px;margin-left: 20px;background:#F2F6FC">
                                    <img v-if="article.covers[0]" :src="article.covers[0].photoUrl">

                                </div>
                                <div @click="uploadArticle(1) " v-show="coverCount>1"
                                     style="height: 100px;width: 100px;margin-left: 20px;background:#F2F6FC">
                                    <img v-if="article.covers[1]" :src="article.covers[1].photoUrl">
                                </div>
                                <div @click="uploadArticle(2)" v-show="coverCount>2"
                                     style="height: 100px;width: 100px;margin-left: 20px;background:#F2F6FC">
                                    <img v-if="article.covers[2]" :src="article.covers[2].photoUrl">
                                </div>

                                <el-dialog :visible.sync="dialogVisible">
                                    <img width="100%" :src="dialogImageUrl" alt="">
                                </el-dialog>
                            </div>
                        </el-form-item>
                        <!--//在主页的发布状态，0未发布，1发布-->
                        <el-form-item label="站内发布">

                            <el-radio v-model="article.publicStatus" :label="0">不发布</el-radio>
                            <el-radio v-model="article.publicStatus" :label="1">发布</el-radio>

                        </el-form-item>

                    </el-form>
                </div>


            </div>
            <div class="bton">
                <el-button style="margin-top: 10px;float: right ;margin-right: 10px"
                           @click="saveArticle()">保存
                </el-button>
            </div>
        </div>

        <div class="footer-list">
            <div style="margin: 0 auto;width: 80%"><span style="width: 100px">@tomweb.xyz</span></div>
        </div>
        <div>
            <el-dialog
                    :close-on-click-modal="false"
                    title="提示"
                    :visible.sync="uploadCover"
                    width="80%"
                    center>
                <el-upload
                        :action="uploadUrl"
                        list-type="picture-card"
                        multiple
                        with-credentials
                        name="fileName"
                        :limit="1"
                        :headers="headers"
                        :on-preview="handlePictureCardPreview"
                        :on-success="fileUploadSuccess"
                        :on-remove="handleRemove">
                    <i class="el-icon-plus"></i>
                </el-upload>


                <span slot="footer" class="dialog-footer">
            <el-button @click="uploadCover = false">取 消</el-button>
             <el-button type="primary" @click="uploadCover = false">确 定</el-button>
            </span>
            </el-dialog>

        </div>
    </div>
</template>
<style scoped>
    .box {
        /*background: #d9d9d9;*/
        display: -webkit-flex; /* Safari */
        display: flex;
        flex-direction: column; /*s轴为垂直方向，起点在上沿。**/
        overflow-y: scroll;
        height: 100vh; /*// 关键，设置高度为可见高度的100%；*/
    }

    .container-editor {

    }

    .editor {
        width: 80%;
        max-width: 700px;
        min-width: 600px;
        max-height: 700px;
        overflow-y: scroll;
        margin: 0 auto;
        box-shadow: 1px 1px 1px 1px #cbcbcb, -1px 1px 1px 1px rgba(255, 255, 255, 0.5);
        margin-bottom: 20px;
    }

    .cover {
        width: 80%;
        max-width: 700px;
        min-width: 600px;
        height: auto;
        /*height: 1000px;*/
        margin: 0 auto;
        box-shadow: 1px 1px 1px 1px #cbcbcb, -1px 1px 1px 1px rgba(255, 255, 255, 0.5)
    }

    .bton {
        width: 80%;
        max-width: 700px;
        min-width: 600px;
        height: 50px;
        /*height: 1000px;*/
        margin: 20px auto;
        box-shadow: 1px 1px 1px 1px #cbcbcb, -1px 1px 1px 1px rgba(255, 255, 255, 0.5)
    }

    .cove-upload {
        display: flex;
        flex-direction: row;
        margin: 20px auto;


    }

    .header-list {
        background: #FFFFFF;
        height: 60px;
        box-shadow: 1px 1px 1px 1px #cbcbcb, -1px 1px 1px 1px rgba(255, 255, 255, 0.5)
    }

    .footer-list {
        height: 60px;
        justify-content: center;
        /*width: 100%;*/
        margin: 0 auto;
        line-height: 60px;
    }


</style>
<script>
    import { Loading } from 'element-ui';
    import E from 'wangeditor'

    import {ImageDrop} from 'quill-image-drop-module';

    // Quill.register('modules/imageDrop', ImageDrop);
    export default {
        components: {

        },
        data() {
            return {
                editor:null,

                coverCount: 1,
                uploadCover: false,
                uploadCoverItem: null,
                dialogVisible: false,
                article: {
                    //简介
                    abstractTxt: null,
                    //百家号appId
                    appId: null,
                    //文章内容
                    articleBody: null,

                    articleId: null,
                    articleUrl: null,
                    collection: null,

                    //封面
                    covers: [],
                    createdOn: null,
                    id: null,
                    isDelete: null,
                    nid: null,
                    publicStatus: null,
                    status: null,
                    //文章标题
                    title: null,
                    type: null,
                    updateOn: null,
                    updatedAt: null,
                },
                save: false,
                //预览照片地址
                dialogImageUrl: null,
                headers: {
                    token: null
                },
                // uploadUrl:'http://localhost:8010/adminApi/file'
                uploadUrl:'/adminApi/file'
            }
        },
        created() {
            this.article.id = this.$route.query.id
            this.headers.token = sessionStorage.getItem('token');
            // this.loadArticleDetail(this.article.id);
        },
        mounted() {
            // this.article.id=this.$route.query.id

            if (this.article.id) {
                this.loadArticleDetail(this.article.id);
            }
            var editor = new E(this.$refs.editor)
            editor.customConfig.onchange = (html) => {
                this.article.articleBody = html
            }


            editor.customConfig.uploadImgShowBase64 = false;
            editor.customConfig.uploadImgServer =this.uploadUrl
            editor.customConfig.uploadImgParams = {
                // 如果版本 <=v3.1.0 ，属性值会自动进行 encode ，此处无需 encode
                // 如果版本 >=v3.1.1 ，属性值不会自动 encode ，如有需要自己手动 encode
                token: this.headers.token
            }
            editor.customConfig.uploadFileName = 'fileName'
            editor.customConfig.withCredentials = true
            editor.customConfig.uploadImgHooks = {

                before: function (xhr, editor, files) {

                    // Loading.service(loading)
                    // 图片上传之前触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

                    // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
                    // return {
                    //     prevent: true,
                    //     msg: '放弃上传'
                    // }
                },
                success: function (xhr, editor, result) {
                    // Loading.close()
                    // 图片上传并返回结果，图片插入成功之后触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                },
                fail: function (xhr, editor, result) {
                    // Loading.close()
                    // 图片上传并返回结果，但图片插入错误时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
                },
                error: function (xhr, editor)
                {
                    // Loading.close()
                    // 图片上传出错时触发
                    // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
                },

                // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
                // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
                customInsert: function (insertImg, result, editor) {
                    // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                    // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

                    // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
                    console.log("res",result)
                    var url = result.data
                    insertImg(url)

                    // result 必须是一个 JSON 格式字符串！！！否则报错
                }
            }

            editor.create();
            this.editor=editor;

        },
        methods: {
            logout() {
                sessionStorage.removeItem('token');
                this.$router.push('/');
            },

            //size-change
            loadArticleDetail(id) {
                console.log(this.article)
                this.$axios.get("/adminApi/aritcle", {params: {id}}).then((res) => {
                    if (res.data.code == "SUCCESS") {
                        this.article = res.data.data;
                        this.editor.txt.append(this.article.articleBody)
                    } else {
                        this.$message.error(res.data.msg)
                    }
                }).catch((res) => {
                    console.log(res);
                    this.$message.error(res)
                })
            },

            handlePictureCardPreview(file) {
                console.log(file);
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            uploadArticle(i) {
                this.uploadCover = true;
                this.uploadCoverItem = i;

            },
            //保存文章
            saveArticle() {
                if (this.save) {
                    console.log("正在保存中……")
                    return;
                }
                this.save = true;
                if (this.coverCount == 1) {
                    var covers = [];
                    covers[0]=this.article.covers[0]
                    this.article.covers = covers;
                }else {
                    if (this.article.covers.length<this.coverCount){
                        this.$message.error("请上传图片");
                        return;
                    }

                }

                this.$axios.put('adminApi/aritcle', this.article).then(res => {
                    this.save = false;
                    if (res.data.code == "SUCCESS") {
                        this.$message.success(res.data.msg);
                        this.$router.push("/articleList");
                        return;
                    }
                }).catch(res => {
                    this.save = false;
                })
            },
            //文件上传成功时
            fileUploadSuccess(response, file, fileList) {
                // debugger
                console.log(response)
                if (response.code == "SUCCESS") {
                    var c = this.createCover(response.data);
                    this.article.covers = this.article.covers || [];
                    this.article.covers[this.uploadCoverItem] = c;
                }
            },
            //移除文件时
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            createCover(path) {
                var cover = {
                    articleId: this.article.id,
                    photoUrl: path,
                    orderNo: this.uploadCoverItem + 1,
                }
                return cover;
            }
,

        }

    };
</script>
