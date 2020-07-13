<template name="articleList">
    <div class="box">
        <div class="header-list">
            <span style="line-height: 60px;margin-left: 30px">爱旅游爱工作</span>
            <span style="line-height: 60px;margin-right: 30px;float: right"><a href="/" @click="logout()">退出</a></span>
        </div>
        <div class="container-list" v-loading="syncBj">

            <!--                <div class="article-menu"></div>-->
            <div class="artilce-list">
                <div style="display: flex; flex-direction: row">
                    <el-button @click="syncBjArticle" v-loading="syncBj">从百家号同步<i class="el-icon-bottom"/></el-button>
                    <div style="width: 200px;margin-left: 10px">  <el-input v-model="baseQuery.title" ></el-input></div>
                    <el-button style="margin-left: 10px" @click="baseQuery.page=1;loadArticleList()"><i class="el-icon-search"></i>查询</el-button>
                    <el-button style="margin-left: 10px" @click="addArticle()">添加<i class="el-icon-circle-plus-outline" ></i></el-button>

                </div>
                <el-table @cell-click="articleDetail"
                          v-loading="load"
                          :data="tableData"
                          style="width: 100%">
                    <el-table-column
                            prop="title"
                            label="标题"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="createdOn"
                            label="同步日期"
                            width="180">
                    </el-table-column>

                    <el-table-column
                            fixed="right"
                            label="操作"
                            width="160">
                        <template slot-scope="scope">
<!--                            <el-button type="text" size="small">站内显示</el-button>-->
                            <el-button type="text" @click="deleteAricle(scope.row)" size="small">删除</el-button>
                        </template>
                    </el-table-column>


                </el-table>
            </div>
            <div class="block">
                <el-pagination
                        @size-change="loadArticleList()"
                        @current-change="loadArticleList()"
                        :current-page.sync="baseQuery.page"
                        :page-size="baseQuery.size"
                        layout="total, prev, pager, next"
                        :total="baseQuery.total">
                </el-pagination>
            </div>
            <!--                <div class="artilce-tips"></div>-->

        </div>
        <div class="footer-list">
            <div style="margin: 0 auto;width: 80%"><span style="width: 100px">@tomweb.xyz</span></div>
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

    .header-list {
        background: #FFFFFF;
        height: 60px;
        box-shadow: 1px 1px 1px 1px #cbcbcb, -1px 1px 1px 1px rgba(255, 255, 255, 0.5)
    }

    .container-list {
        margin: 0 auto;
        width: 90%;
        -webkit-box-flex: 1; /*	//设置1等份占满全屏*/
        min-width: 800px;
        /*background: #FFFFFF;*/
        display: -webkit-flex; /* Safari */
        display: flex;
        flex-direction: column;

    }

    .article-menu {

    }

    .artilce-list {
        margin-top: 10px;
        margin-bottom: 10px;
        width: 100%;
    }

    .artilce-tips {

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
    export default {
        data() {
            return {
                load:false,
                syncBj:false,
                tableData: [],
                baseQuery: {
                    page: 1,
                    size: 20,
                    total: 0,
                    totalPage: 0,
                    title:null
                }
            }
        },
        mounted() {
            this.loadArticleList();
        },
        methods: {
            logout() {
                localStorage.removeItem('token');
                this.$router.push('/');
            },
            //size-change
            loadArticleList() {
                if (this.load){
                    return;
                }
                this.load=true;
                this.$axios.get('/adminApi/aritcleList', {params: this.baseQuery}).then((res) => {
                    if (res.data.code == "SUCCESS") {
                        this.tableData = res.data.data;
                        this.baseQuery.total = res.data.total;
                        this.baseQuery.page = res.data.page;
                        this.baseQuery.totalPage = res.data.totalPage;
                        this.load=false;
                    } else {
                        this.load=false;
                        this.$message.error(res.data.msg)
                    }
                }).catch((res) => {
                    this.load=false;
                    console.log(res);
                    this.$message.error(res)
                })
            },
            articleDetail(row,column) {
                // console.log(row, column);

                if (column.label=="操作"){
                    return;
                }
                // console.log(row)
                var id=row.id
                this.$router.push({
                    path:'/articleDetail',
                    query:{
                        id:id
                    }
                })
            },
            addArticle(){
                this.$router.push({
                    path:'/articleDetail',
                    query:{
                        add:true
                    }
                })
                return;
            },
            syncBjArticle(){
                this.syncBj=true
                this.$axios.get("/adminApi/syncBjaritcle").then(res=>{
                    this.syncBj=false;
                     if (res.data.code="SUCCESS"){
                         this.$message.success("同步成功");
                         return;
                     }else {
                         this.$message.error("同步失败");
                         return;;
                     }
                }).catch(res=>{
                    this.$message.error(res)
                    this.syncBj=false;
                })
            },
            //删除文章
            deleteAricle(row){
                var that =this;
                console.log(row);
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    that.$axios.delete("/adminApi/article/"+row.id).then(res=>{
                        if (res.data.code=="SUCCESS"){
                            that.$message.success("删除成功");
                            that.loadArticleList();
                            return;
                        }
                        that.$message.error("删除失败");
                        return;
                    }).catch(res=>{
                        that.$message.error("删除失败");
                        return;
                    })


                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });

                this.loadArticleList()
            }
        }
    };
</script>
