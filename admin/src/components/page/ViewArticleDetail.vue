<template name="articleDetail">
    <div>
{{article.articleBody}}
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
        max-width: 1000px;
        min-width: 800px;
        height: 800px;
        overflow-y: scroll;
        margin: 0 auto;
        box-shadow: 1px 1px 1px 1px #cbcbcb, -1px 1px 1px 1px rgba(255, 255, 255, 0.5);
        margin-bottom: 20px;
    }

    .cover {
        width: 80%;
        max-width: 1000px;
        min-width: 800px;
        height: auto;
        /*height: 1000px;*/
        margin: 0 auto;
        box-shadow: 1px 1px 1px 1px #cbcbcb, -1px 1px 1px 1px rgba(255, 255, 255, 0.5)
    }

    .bton {
        width: 80%;
        max-width: 1000px;
        min-width: 800px;
        height: 50px;
        /*height: 1000px;*/
        margin: 20px auto;
        box-shadow: 1px 1px 1px 1px #cbcbcb, -1px 1px 1px 1px rgba(255, 255, 255, 0.5)
    }
    .w-e-text-container{
        height: 600px !important;/*!important是重点，因为原div是行内样式设置的高度300px*/
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
    import {Loading} from 'element-ui';
    import E from 'wangeditor'
    import config from '../../../vue.config'
    import VueUeditorWrap from "vue-ueditor-wrap";

    import {ImageDrop} from 'quill-image-drop-module';

    // Quill.register('modules/imageDrop', ImageDrop);
    export default {
        components: {
            VueUeditorWrap
        },
        data() {
            return {
                myConfig: {
                    // 编辑器不自动被内容撑高
                    autoHeightEnabled: true,
                    // 初始容器高度
                    initialFrameHeight: '680',
                    // 初始容器宽度
                    initialFrameWidth: '100%',
                    // 上传文件接口（这个地址是我为了方便各位体验文件上传功能搭建的临时接口，请勿在生产环境使用！！！）
                    serverUrl: this.uploadUrl,
                    // UEditor 资源文件的存放路径，如果你使用的是 vue-cli 生成的项目，通常不需要设置该选项，vue-ueditor-wrap 会自动处理常见的情况，如果需要特殊配置，参考下方的常见问题2
                    UEDITOR_HOME_URL: config.publicPath+'UEditor/'

                },
                editor: null,

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
                add: false,
                save: false,
                //预览照片地址
                dialogImageUrl: null,
                headers: {
                    token: null
                },
                // uploadUrl:'http://localhost:8010/adminApi/file'
                uploadUrl: '/adminApi/file'
            }
        },
        created() {
            this.article.id = this.$route.query.id
            this.headers.token = localStorage.getItem('token');
            // this.loadArticleDetail(this.article.id);
        },
        mounted() {
            // this.article.id=this.$route.query.id
            this.article.id = this.$route.query.id
            this.add = this.$route.query.add || false;
            var that = this;
            if (this.article.id) {
                this.loadArticleDetail(this.article.id);
            } else {
                var str = localStorage.getItem("article") || null;
                if (str) {
                    var article = JSON.parse(str);

                    this.article = article
                    localStorage.removeItem("article")

                } else {
                    for (var key in this.article) {
                        this.article[key] = null;
                    }
                    this.article.covers = [];
                }


            }
            // var editor = new E(this.$refs.editor)
            // editor.customConfig.onchange = (html) => {
            //     this.article.articleBody = html
            // }
            //
            //
            // editor.customConfig.uploadImgShowBase64 = false;
            // editor.customConfig.uploadImgServer = this.uploadUrl
            // editor.customConfig.uploadImgParams = {
            //     // 如果版本 <=v3.1.0 ，属性值会自动进行 encode ，此处无需 encode
            //     // 如果版本 >=v3.1.1 ，属性值不会自动 encode ，如有需要自己手动 encode
            //     token: this.headers.token
            // }
            // editor.customConfig.uploadFileName = 'fileName'
            // editor.customConfig.withCredentials = true
            // editor.customConfig.uploadImgHooks = {
            //
            //     before: function (xhr, editor, files) {
            //
            //         // Loading.service(loading)
            //         // 图片上传之前触发
            //         // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件
            //
            //         // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
            //         // return {
            //         //     prevent: true,
            //         //     msg: '放弃上传'
            //         // }
            //     },
            //     success: function (xhr, editor, result) {
            //         // Loading.close()
            //         // 图片上传并返回结果，图片插入成功之后触发
            //         // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
            //     },
            //     fail: function (xhr, editor, result) {
            //         // Loading.close()
            //         // 图片上传并返回结果，但图片插入错误时触发
            //         // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
            //     },
            //     error: function (xhr, editor) {
            //         // Loading.close()
            //         // 图片上传出错时触发
            //         // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
            //     },
            //
            //     // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
            //     // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
            //     customInsert: function (insertImg, result, editor) {
            //         // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            //         // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
            //
            //         // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            //         console.log("res", result)
            //         if (result.code == "SUCCESS") {
            //             var url = result.data
            //             insertImg(url)
            //         } else {
            //             that.$message.error("保存错误！");
            //         }
            //
            //
            //         // result 必须是一个 JSON 格式字符串！！！否则报错
            //     }
            // }
            //
            // editor.create();
            //
            // this.editor = editor;
            // this.editor.txt.append(this.article.articleBody)

        },
        methods: {
            logout() {
                localStorage.removeItem('token');
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
                    if (this.article.covers[0]) {
                        covers[0] = this.article.covers[0]
                        this.article.covers = covers;
                    }

                } else {
                    if (this.article.covers.length < this.coverCount) {
                        this.$message.error("请上传图片");
                        return;
                    }

                }
                if (this.add) {
                    this.addToSever();
                } else {
                    this.updateToSever();
                }


            },
            saveArticlelocal() {
                var str = JSON.stringify(this.article);
                localStorage.setItem("article", str);
            },
            viewArticle() {
                var str = JSON.stringify(this.article);
                localStorage.setItem("article", str);
                this.$router.push("view")
            },
            updateToSever() {
                this.$axios.put('/adminApi/aritcle', this.article).then((res) => {
                    this.save = false;
                    if (res.data.code == "SUCCESS") {
                        this.$message.success(res.data.msg);
                        for (let articleKey in this.article) {
                            this.article[articleKey]=null;
                        }
                        this.article.covers=[];
                        // localStorage.setItem("article",null)
                        localStorage.removeItem("article")

                        this.$router.push("/articleList");
                        return;
                    } else {
                        this.save = false;
                        this.$message.error("保存错误");
                    }
                }).catch((res) => {
                    this.save = false;
                })
            },
            addToSever() {
                this.$axios.post('/adminApi/aritcle', this.article).then((res) => {
                    this.save = false;
                    if (res.data.code == "SUCCESS") {
                        this.$message.success(res.data.msg);

                        localStorage.removeItem("article")

                        this.$router.push("/articleList");
                        return;
                    } else {
                        this.save = false;
                        this.$message.error("保存错误");
                    }
                }).catch((res) => {
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
                } else {
                    this.$message.error("上传图片失败，请重新上传");
                }
            },
            //移除文件时
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            createCover(path) {
                if (!path) {
                    this.$message.error("图片路径为空");
                }
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
